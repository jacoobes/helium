package components.buttons.icon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ApplicationScope
import components.buttons.HoverableFlatButton

@Composable
fun ApplicationScope.ExitButton() {

    HoverableFlatButton(
        onClick = ::exitApplication,
        enabled = true,
    ) {
        FlatIcon(
            icon = Icons.Sharp.Close,
            contentDescription = "Exit",
        )
    }

}