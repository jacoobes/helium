package components.buttons.icon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.helium.scalable.svg.helium
import components.buttons.HoverableFlatButton
import testBorder

@Composable
fun Helium() {
    HoverableFlatButton(
        enabled = false
    ) {
        Icon(
            painter = painterResource("scalable/helium.svg"),
            contentDescription = "logo",
            tint = Color.Unspecified,
        )
    }
}