package components.buttons.text

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ApplicationScope
import components.buttons.FlatText
import components.buttons.HoverableFlatButton

@Composable
fun ApplicationScope.File(
    paddingValues: PaddingValues
) {
    val (isOpen, setOpen) = remember { mutableStateOf(false) }
    HoverableFlatButton(
        onClick = {
            setOpen(!isOpen)
        },
        paddingValues = paddingValues,
    ) { scheme ->
        FlatText("File", scheme)

    }
}