package components.buttons.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import components.buttons.HoverableFlatButton

@Composable
fun Helium() {
    HoverableFlatButton(
        enabled = false
    ) {
        Icon(
                painter = painterResource("scalable/helium.svg"),
                contentDescription = "logo",
        )
    }
}