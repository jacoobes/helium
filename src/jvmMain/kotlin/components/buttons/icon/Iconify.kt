package components.buttons.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.FrameWindowScope
import components.buttons.HoverableFlatButton
import javax.swing.JFrame

@Composable
fun FrameWindowScope.Iconify() {
    HoverableFlatButton(
        onClick = {
            if (window.isFocused) {
                window.extendedState = JFrame.ICONIFIED
            }
        },
        enabled = true,
    ) {
        FlatIcon(
            icon = Icons.Sharp.KeyboardArrowDown,
            contentDescription = "Minimize",
        )
    }
}