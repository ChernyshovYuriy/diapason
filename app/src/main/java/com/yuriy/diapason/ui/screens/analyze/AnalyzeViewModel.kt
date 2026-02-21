package com.yuriy.diapason.ui.screens.analyze

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriy.diapason.analyzer.FachClassifier
import com.yuriy.diapason.analyzer.FachMatch
import com.yuriy.diapason.analyzer.VoiceAnalyzer
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
        val statusMessage: String = "Listening… sing from your lowest to highest note, then back"
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

class AnalyzeViewModel : ViewModel() {

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
        _uiState.value = AnalyzeUiState.Recording()
        analyzer.start()
    }

    fun stopRecording() {
        Log.i(TAG, "stopRecording()")
        if (!analyzer.isRunning) return

        _uiState.value = AnalyzeUiState.Recording(
            statusMessage = "Analyzing your voice…",
            sampleCount = (uiState.value as? AnalyzeUiState.Recording)?.sampleCount ?: 0
        )

        val profile = analyzer.stop()

        if (profile == null) {
            _uiState.value = AnalyzeUiState.InsufficientData(
                "Not enough data. Please sing sustained notes for at least 20–30 seconds."
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
        if (analyzer.isRunning) analyzer.stop()
    }
}
