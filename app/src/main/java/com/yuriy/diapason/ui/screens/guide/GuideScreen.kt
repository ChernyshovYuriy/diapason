package com.yuriy.diapason.ui.screens.guide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GuideScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            text = "How to Use Diapason",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Follow these steps for the most accurate result",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )

        // ── Steps ─────────────────────────────────────────────────────────────
        StepCard(
            number = "1",
            icon = Icons.Filled.VolumeMute,
            title = "Find a quiet room",
            body = "Background noise lowers the confidence of pitch detection. " +
                    "Close windows, turn off fans, and move away from TVs or music."
        )
        StepCard(
            number = "2",
            icon = Icons.Filled.MusicNote,
            title = "Warm up first",
            body = "Do 5–10 minutes of vocal warm-ups before starting. " +
                    "Cold voices produce unreliable results and may not reach their true range " +
                    "extremes."
        )
        StepCard(
            number = "3",
            icon = Icons.Filled.GraphicEq,
            title = "Sing sustained notes",
            body = "Hold each note for at least 2–3 seconds. Diapason needs a stable, sustained " +
                    "tone " +
                    "to detect pitch reliably — speech and slides between notes are filtered out."
        )
        StepCard(
            number = "4",
            icon = Icons.Filled.Timer,
            title = "Cover your full range",
            body = "Start at your lowest comfortable note and step upward chromatically or by " +
                    "scale to your highest. " +
                    "Then come back down. This helps detect both your absolute range and your " +
                    "passaggio (register break). " +
                    "Aim for at least 30–45 seconds of singing."
        )
        StepCard(
            number = "5",
            icon = Icons.Filled.CheckCircle,
            title = "Press Stop and review",
            body = "Diapason will show your top voice type match with a confidence score, " +
                    "your measured range, tessitura, and estimated passaggio. Full scoring " +
                    "detail is in Logcat."
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── What is Fach ──────────────────────────────────────────────────────
        Text(
            text = "What is Fach?",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "\"Fach\" (pronounced /fax/, German for \"compartment\") is the classical system " +
                    "used in opera houses to categorise singers by voice type. Knowing your Fach helps " +
                    "you identify appropriate repertoire, protect your voice, and communicate with " +
                    "casting directors and voice teachers.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Beyond just \"soprano\" or \"bass\", the Fach system distinguishes 19+ subtypes " +
                    "based on range, tessitura (where the voice sits most comfortably), passaggio " +
                    "(the register break between chest and head voice), and timbre (tone colour).",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── What Diapason measures ────────────────────────────────────────────
        Text(
            text = "What Diapason Measures",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(10.dp))

        MeasurementRow(
            term = "Absolute Range",
            def = "The lowest and highest clean pitch detected during your session."
        )
        MeasurementRow(
            term = "Tessitura",
            def = "The 20th–80th percentile of your pitch samples — where your voice naturally " +
                    "and comfortably spends most of its time."
        )
        MeasurementRow(
            term = "Passaggio",
            def = "Estimated from the zone of highest pitch instability in your session — the " +
                    "bridge point between your chest and head registers."
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── Limitation disclaimer ─────────────────────────────────────────────
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 1.dp)
                )
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = "What Diapason Cannot Measure",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Timbre (tone colour) and vocal weight are essential to accurate Fach classification " +
                                "but cannot be reliably measured by a phone microphone. A Dramatic Soprano and a Lyric Soprano " +
                                "can hit the same notes — only a trained ear can distinguish them. " +
                                "Use Diapason's result as a strong starting point, not a final verdict.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.85f)
                    )
                }
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun StepCard(number: String, icon: ImageVector, title: String, body: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            // Step number badge
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(32.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                    Text(
                        number, style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        icon, contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    body, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun MeasurementRow(term: String, def: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            term, style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold
        )
        Text(
            def, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
