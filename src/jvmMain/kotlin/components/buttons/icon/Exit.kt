package components.buttons.icon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope
import components.buttons.HoverableFlatButton

@Composable
fun ApplicationScope.ExitButton(
    paddingValues: PaddingValues
) {

    HoverableFlatButton(
        onClick = ::exitApplication,
        paddingValues = paddingValues,
    ) { scheme ->
        FlatIcon(
            icon = Icons.Sharp.Close,
            contentDescription = "Exit",
            scheme = scheme
        )
    }

}