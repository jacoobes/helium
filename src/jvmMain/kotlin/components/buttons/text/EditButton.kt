package components.buttons.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.FrameWindowScope
import components.buttons.FlatText
import components.buttons.HoverableFlatButton

@Composable
fun FrameWindowScope.Edit() {
    val (isOpen, setOpen) = remember { mutableStateOf(false) }
    HoverableFlatButton(
        onClick = {
            setOpen(!isOpen) },
    ) { scheme ->
        FlatText("Edit", scheme)
    }
}