package components.drawers.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.painterResource
import org.xml.sax.InputSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.LightMode(canSwitchMode: Boolean = true, /*darkMode: MutableState<Boolean>*/ ) {
    Spacer(Modifier.weight(.9f))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledTonalIconButton(
            onClick = {},
            //placeholder
            content = { Icon(painterResource("scalable/light_mode_24.svg"), contentDescription = null) }
        )
    }
}