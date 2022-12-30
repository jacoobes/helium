package components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope

@Composable
fun ApplicationScope.File(
    paddingValues: PaddingValues
) {
    HoverableFlatButton(
        paddingValues = paddingValues,
        onClick = {
            println("hello")
        }
    ) { scheme ->
        FlatText("File", scheme)
    }
}