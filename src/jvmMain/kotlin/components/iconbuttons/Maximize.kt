package components.iconbuttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.FrameWindowScope
import javax.swing.JFrame


@Composable
fun FrameWindowScope.Maximize(
    paddingValues: PaddingValues
) {

    val (isMaximized, setMaximized) = remember { mutableStateOf(window.extendedState == JFrame.MAXIMIZED_BOTH) }
    val currentIcon = if (isMaximized) {
        painterResource("scalable/fullscreen_black_24dp.svg")
    } else {
        painterResource("scalable/fullscreen_exit_black_24dp.svg")
    }

    HoverableFlatIconButton(
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
        paddingValues = paddingValues,
    ) { scheme ->
        FlatIcon(
            icon = currentIcon,
            contentDescription = "Maximize",
            scheme = scheme
        )
    }
}
