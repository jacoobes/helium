package components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HoverableFlatButton(
    onClick: () -> Unit = {},
    paddingValues: PaddingValues? = null,
    colorPairOnHover: Pair<Color, Color> = MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer,
    colorPairNotHover: Pair<Color, Color> = MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer,
    content: @Composable BoxScope.(Pair<Color, Color>) -> Unit,
) {
    val (isHovered, setHovered) = remember { mutableStateOf(false) }
    val currentColorScheme = if (isHovered) {
        colorPairOnHover
    } else {
        colorPairNotHover
    }
    Box(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .onPointerEvent(PointerEventType.Enter) { setHovered(true) }
            .onPointerEvent(PointerEventType.Exit) { setHovered(false) }
            .background(currentColorScheme.first)
            .padding(paddingValues ?: PaddingValues(5.dp))
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        content(currentColorScheme)
    }
}