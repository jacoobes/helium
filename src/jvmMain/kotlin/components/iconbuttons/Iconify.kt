package components.iconbuttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import javax.swing.JFrame

@Composable
fun FrameWindowScope.Iconify(paddingValues: PaddingValues) {

    HoverableFlatIconButton(
        onClick = {
            if (window.isFocused) {
                window.extendedState = JFrame.ICONIFIED
            }
        },
        paddingValues = paddingValues,
    ) { scheme ->
        FlatIcon(
            icon = Icons.Sharp.KeyboardArrowDown,
            contentDescription = "Minimize",
            scheme = scheme
        )
    }
}