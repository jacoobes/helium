package components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import org.pushingpixels.aurora.theming.businessBlueSteelSkin
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations

@Composable
fun AuroraApplicationScope.LoadingHome(settingsLoaded: Boolean) {
    AuroraWindow(
        skin = businessBlueSteelSkin(),
        state = WindowState(
            WindowPlacement.Floating,
            false,
            WindowPosition.Aligned(Alignment.Center),
            DpSize(500.dp, 250.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.None,
        onCloseRequest = ::exitApplication
    ) {
        if(settingsLoaded) {
            //idk if this works
            LaunchedEffect(Unit) {
                window.dispose()
            }
        } else {
            //TODO
            Text("LOADING")
        }
    }
}