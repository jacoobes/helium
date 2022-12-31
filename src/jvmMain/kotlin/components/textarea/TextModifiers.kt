package components.textarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Chip
import androidx.compose.material3.Divider
import androidx.compose.material3.Text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import components.DividerLessAlpha


@Composable
fun TextModifiers() {
    Row {
        Text("Sup")
        Text("Sup")
    }
    DividerLessAlpha(alpha = .50f)
}