package components.buttons.text

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import components.Tooltip
import components.buttons.FlatText
import components.buttons.HoverableFlatButton

@Composable
fun FrameWindowScope.File() {
    val isOpen = remember { mutableStateOf(false) }
    Box {
        HoverableFlatButton(
            onClick = {
                isOpen.value = !isOpen.value
            },
        ) { scheme ->
            FlatText("File", scheme)
        }
    }

}