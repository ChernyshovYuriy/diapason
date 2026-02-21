package com.yuriy.diapason.ui.screens.analyze

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yuriy.diapason.R
import com.yuriy.diapason.analyzer.FachClassifier
import com.yuriy.diapason.analyzer.FachMatch
import com.yuriy.diapason.analyzer.VoiceAnalyzer
import com.yuriy.diapason.analyzer.VoiceAnalyzerStrings
import com.yuriy.diapason.analyzer.VoiceProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "AnalyzeViewModel"

// ── UI State ─────────────────────────────────────────────────────────────────

sealed interface AnalyzeUiState {
    /** Idle — waiting for the user to press Start */
    object Idle : AnalyzeUiState

    /** Actively recording and detecting pitch */
    data class Recording(
        val currentNote: String = "—",
        val currentHz: Float = 0f,
        val sampleCount: Int = 0,
        val statusMessage: String = ""
    ) : AnalyzeUiState

    /** Processing finished but resulted in insufficient data */
    data class InsufficientData(val reason: String) : AnalyzeUiState

    /** Full analysis result ready — navigate to ResultsScreen */
    data class ResultReady(
        val profile: VoiceProfile,
        val matches: List<FachMatch>
    ) : AnalyzeUiState
}

// ─────────────────────────────────────────────────────────────────────────────

class AnalyzeViewModel(application: Application) : AndroidViewModel(application) {

    private fun getString(resId: Int): String = getApplication<Application>().getString(resId)

    private val _uiState = MutableStateFlow<AnalyzeUiState>(AnalyzeUiState.Idle)
    val uiState: StateFlow<AnalyzeUiState> = _uiState.asStateFlow()

    private val analyzer = VoiceAnalyzer(viewModelScope)

    // Holds the last result so ResultsScreen can retrieve it after navigation
    var lastResult: AnalyzeUiState.ResultReady? = null
        private set

    init {
        analyzer.onPitchDetected = { hz, noteName ->
            _uiState.update { current ->
                if (current is AnalyzeUiState.Recording) {
                    current.copy(
                        currentNote = noteName,
                        currentHz = hz,
                        sampleCount = current.sampleCount + 1
                    )
                } else current
            }
        }

        analyzer.onStatusUpdate = { message ->
            _uiState.update { current ->
                if (current is AnalyzeUiState.Recording) current.copy(statusMessage = message)
                else current
            }
        }
    }

    fun startRecording() {
        Log.i(TAG, "startRecording()")
        _uiState.value = AnalyzeUiState.Recording(
            statusMessage = getString(R.string.analyze_status_listening)
        )
        analyzer.start(
            VoiceAnalyzerStrings(
                listeningMessage = getString(R.string.analyze_status_listening_short),
                micInitError = getString(R.string.analyze_status_mic_error),
                tooFewSamples = getString(R.string.analyze_status_too_few_samples)
            )
        )
    }

    fun stopRecording() {
        Log.i(TAG, "stopRecording()")
        if (!analyzer.isRunning) return

        _uiState.value = AnalyzeUiState.Recording(
            statusMessage = getString(R.string.analyze_status_analyzing),
            sampleCount = (uiState.value as? AnalyzeUiState.Recording)?.sampleCount ?: 0
        )

        val profile = analyzer.stop(
            tooFewSamplesMessage = getString(R.string.analyze_status_too_few_samples)
        )

        if (profile == null) {
            _uiState.value = AnalyzeUiState.InsufficientData(
                getString(R.string.analyze_error_insufficient)
            )
            return
        }

        val matches = FachClassifier.classify(profile)
        val result = AnalyzeUiState.ResultReady(profile = profile, matches = matches)
        lastResult = result
        _uiState.value = result
    }

    fun resetToIdle() {
        _uiState.value = AnalyzeUiState.Idle
    }

    override fun onCleared() {
        super.onCleared()
        if (analyzer.isRunning) analyzer.stop(getString(R.string.analyze_status_too_few_samples))
    }
}
