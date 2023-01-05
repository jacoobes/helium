package components.buttons.icon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.FrameWindowScope
import components.buttons.HoverableFlatButton
import javax.swing.JFrame


@Composable
fun FrameWindowScope.Maximize() {

    val (isMaximized, setMaximized) = remember { mutableStateOf(window.extendedState == JFrame.MAXIMIZED_BOTH) }
    val currentIcon = if (isMaximized) {
        painterResource("scalable/fullscreen_black_24dp.svg")
    } else {
        painterResource("scalable/fullscreen_exit_black_24dp.svg")
    }

    HoverableFlatButton(
        onClick = {
            setMaximized(!isMaximized)
            if (window.isFocused) {
                if (window.extendedState == JFrame.MAXIMIZED_BOTH) {
                    window.extendedState = JFrame.NORMAL
                } else {
                    window.extendedState = JFrame.MAXIMIZED_BOTH
                }
            }
        },
        enabled = true,
    ) {
        FlatIcon(
            icon = currentIcon,
            contentDescription = "Maximize",
        )
    }
}
