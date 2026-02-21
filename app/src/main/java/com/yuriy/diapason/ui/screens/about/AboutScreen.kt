package com.yuriy.diapason.ui.screens.about

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
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Science
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────────────────────────────────────
// Keep these in sync with app/build.gradle.kts → versionName / versionCode
// ─────────────────────────────────────────────────────────────────────────────
private const val APP_VERSION = "1.0"
private const val APP_BUILD = "1"

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        // ── Hero ─────────────────────────────────────────────────────────────
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.GraphicEq,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Diapason",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Voice Range Classifier",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Version $APP_VERSION (build $APP_BUILD)",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }

        Spacer(Modifier.height(24.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── About the app ─────────────────────────────────────────────────────
        SectionTitle("About")
        Text(
            text = "Diapason helps singers and voice teachers explore and categorise vocal range " +
                    "using the classical German Fach system. It listens to sustained notes through " +
                    "your device microphone, builds an acoustic profile in real time, and matches " +
                    "it against 19 documented voice types — from Coloratura Soprano to Contrabass Oktavist.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "The app is intended as an educational starting point. Timbre and vocal weight — " +
                    "essential for a definitive Fach — require a trained human ear and cannot be " +
                    "captured by a microphone alone.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── Creator ───────────────────────────────────────────────────────────
        SectionTitle("Creator")
        InfoRow(
            icon = Icons.Filled.Person,
            label = "Developer",
            value = "Yuriy"
        )
        InfoRow(
            icon = Icons.Filled.Code,
            label = "Stack",
            value = "Kotlin · Jetpack Compose · Material 3"
        )
        InfoRow(
            icon = Icons.Filled.Science,
            label = "Pitch detection",
            value = "YIN algorithm (de Cheveigné & Kawahara, JASA 2002)"
        )
        InfoRow(
            icon = Icons.Filled.MusicNote,
            label = "Voice taxonomy",
            value = "German Fach system — 19 voice categories"
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── Localisation ──────────────────────────────────────────────────────
        SectionTitle("Languages & Localisation")
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Language,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Currently English only",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Localisation is planned. Android's standard string resource system " +
                            "(res/values-xx/strings.xml) will handle static UI text once translations are ready.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Recommended translation approach:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(6.dp))
                TranslationOption(
                    name = "Android built-in (strings.xml)",
                    detail = "Free · zero runtime cost · best for static UI strings. " +
                            "Add res/values-de/, values-it/, etc. No API key needed."
                )
                TranslationOption(
                    name = "Google ML Kit — Translation",
                    detail = "Free · fully on-device · 58 languages. " +
                            "Good for translating dynamic content (role names, descriptions) " +
                            "without network access. Add dependency: com.google.mlkit:translate"
                )
                TranslationOption(
                    name = "Google Cloud Translation API",
                    detail = "Pay-per-use · 100+ languages · highest accuracy. " +
                            "Requires network and a GCP API key. Best if you need " +
                            "server-side pre-translation of the Fach database."
                )
                TranslationOption(
                    name = "DeepL API (Free tier)",
                    detail = "500 000 chars/month free · 31 languages · considered more " +
                            "nuanced than Google for European languages. " +
                            "REST API, easy to integrate."
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── Open source references ─────────────────────────────────────────────
        SectionTitle("References")
        InfoRow(
            icon = Icons.Filled.Science,
            label = "YIN paper",
            value = "de Cheveigné & Kawahara — JASA 111(4), 2002"
        )
        InfoRow(
            icon = Icons.Filled.MusicNote,
            label = "Fach taxonomy",
            value = "Kloiber / Maehder / Melchert — Handbuch der Oper"
        )

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))

        // ── Feedback ──────────────────────────────────────────────────────────
        SectionTitle("Feedback & Bug Reports")
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.BugReport,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Found a bug or have a feature request? Open an issue on the project repository.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // ── Footer ────────────────────────────────────────────────────────────
        Text(
            text = "© 2025 Yuriy · Diapason $APP_VERSION",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.45f),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )

        Spacer(Modifier.height(24.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
private fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .size(18.dp)
                .padding(top = 1.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Text(
                value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun TranslationOption(name: String, detail: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            "• $name",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            detail,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 10.dp, top = 1.dp)
        )
    }
}
