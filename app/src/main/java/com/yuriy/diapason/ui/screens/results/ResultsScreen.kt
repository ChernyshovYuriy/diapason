package com.yuriy.diapason.ui.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yuriy.diapason.analyzer.FachClassifier
import com.yuriy.diapason.analyzer.FachMatch
import com.yuriy.diapason.analyzer.VoiceProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    profile: VoiceProfile,
    matches: List<FachMatch>,
    onBack: () -> Unit,
    onAnalyzeAgain: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Results") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            // ── Top match (hero card) ──────────────────────────────────────
            matches.firstOrNull()?.let { top ->
                TopMatchCard(match = top)
            }

            Spacer(Modifier.height(16.dp))

            // ── Range statistics ──────────────────────────────────────────
            SectionLabel("Measured Range")
            RangeStatsCard(profile = profile)

            Spacer(Modifier.height(16.dp))

            // ── Runner-up matches ─────────────────────────────────────────
            if (matches.size > 1) {
                SectionLabel("Other Possible Matches")
                matches.drop(1).take(4).forEachIndexed { index, match ->
                    RunnerUpRow(rank = index + 2, match = match)
                    if (index < 3) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Disclaimer ────────────────────────────────────────────────
            DisclaimerCard()

            Spacer(Modifier.height(20.dp))

            // ── Action buttons ────────────────────────────────────────────
            Button(
                onClick = onAnalyzeAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Icon(
                    Icons.Filled.Refresh, contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Analyze Again")
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Sub-composables
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun TopMatchCard(match: FachMatch) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.EmojiEvents,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Best Match",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = match.fach.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Text(
                text = match.fach.category,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = match.fach.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(10.dp))

            // Confidence bar
            val pct = match.score.toFloat() / match.maxScore.toFloat()
            ConfidenceBar(label = "Classification confidence", fraction = pct)

            Spacer(Modifier.height(14.dp))

            // Famous roles
            if (match.fach.famousRoles.isNotEmpty()) {
                Text(
                    text = "Famous roles for this voice",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                match.fach.famousRoles.forEach { role ->
                    Text(
                        text = "• $role",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RangeStatsCard(profile: VoiceProfile) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            StatRow(
                "Lowest note detected", FachClassifier.hzToNoteName(profile.absoluteMinHz),
                "%.0f Hz".format(profile.absoluteMinHz)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            StatRow(
                "Highest note detected", FachClassifier.hzToNoteName(profile.absoluteMaxHz),
                "%.0f Hz".format(profile.absoluteMaxHz)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            StatRow(
                "Tessitura (comfortable zone)",
                "${FachClassifier.hzToNoteName(profile.tessituraLowHz)} – ${
                    FachClassifier.hzToNoteName(
                        profile.tessituraHighHz
                    )
                }",
                "P20–P80"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            StatRow(
                "Estimated passaggio", FachClassifier.hzToNoteName(profile.estimatedPassaggioHz),
                "%.0f Hz".format(profile.estimatedPassaggioHz)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            StatRow(
                "Valid pitch samples", profile.sampleCount.toString(),
                "%.0fs session".format(profile.durationSeconds)
            )
        }
    }
}

@Composable
private fun StatRow(label: String, value: String, sub: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Text(
                sub, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun RunnerUpRow(rank: Int, match: FachMatch) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#$rank",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            modifier = Modifier.width(32.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                match.fach.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                match.fach.category, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = "${match.score}/${match.maxScore}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ConfidenceBar(label: String, fraction: Float) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                label, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
            Text(
                "${(fraction * 100).toInt()}%", style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(Modifier.height(4.dp))
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
                    .then(
                        Modifier.fillMaxWidth(fraction)
                    )
            )
            // Background track
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.20f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx())
                )
                drawRoundRect(
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.80f),
                    size = size.copy(width = size.width * fraction),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx())
                )
            }
        }
    }
}

@Composable
private fun DisclaimerCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
            Icon(
                Icons.Filled.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 2.dp, end = 10.dp)
            )
            Text(
                text = "Fach classification also depends on timbre and vocal weight, which require a trained ear. " +
                        "Use this as an informed starting point — not a definitive diagnosis. " +
                        "A voice teacher will always give the most accurate assessment.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.85f)
            )
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
