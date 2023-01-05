package components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import buttonSizes

@Composable
fun HoverableFlatButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    colorPair: Pair<Color, Color> = MaterialTheme.colorScheme.surface to MaterialTheme.colorScheme.onSurfaceVariant,
    enabled: Boolean = true,
    content: @Composable BoxScope.(Pair<Color, Color>) -> Unit,
) {
    val mod = Modifier
        .background(colorPair.first)
        .then(modifier)
        .then(
            if(enabled) {
                Modifier.clickable(
                    onClick = onClick,
                    role = Role.Button
                )
            } else {
                Modifier
            }
        )


    BoxWithConstraints (
        modifier = mod,
    ) {
        //material spec (ish) https://m3.material.io/components/top-app-bar/specs
        val pad = 12.dp
        val startEnd = PaddingValues(start = pad, end = pad)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(startEnd)
                .size(buttonSizes)
        ) {
            content(colorPair)
        }
    }
}