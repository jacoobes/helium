package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import structs.Settings


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ApplicationScope.SettingsEditor(
    settings: Settings,
    show: MutableState<Boolean>
) {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize((settings.dimensions.width * .50).dp, (settings.dimensions.height * .50).dp)
    )
    val (vis, setVis) = show
    if (vis) {
        HeliumWindow(
            state = state,
            title = "Settings",
            resizable = true,
            onPreviewKeyEvent = {
                if (it.isCtrlPressed && it.key == Key.W) {
                    setVis(false)
                }
                false
            },
            onCloseRequest = { setVis(false) }
        ) {
            Row {
                Box {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {

                    }
                }
            }


            //
//            {
//                "editorFont" : "JetBrainsMono.ttf",
//                "codeFont" : "JetBrainsMono.ttf",
//                "dimensions" : {
//                "width" : 1000,
//                "height" : 900
//            },
//                "theme" : "poo"
//            }


        }
    }
}