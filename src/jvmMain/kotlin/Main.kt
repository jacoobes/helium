import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import components.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import structs.HeliumTheme
import structs.Settings
import structs.loadSettingsAsync
import java.nio.charset.StandardCharsets
val json = Json {
    prettyPrint = true
    encodeDefaults = true
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
fun main() = application {
    //run blocking for now, idk how to asynchronously do it
    val coroutineScope = rememberCoroutineScope()
    val (settings, setSettings) = remember { mutableStateOf<Settings?>(null) }
    val (isSettingsLoaded, setSettingsLoaded) = remember { mutableStateOf(false) }
    coroutineScope.launch {
        val content = loadSettingsAsync().await().flip()
        setSettings(json.decodeFromString(StandardCharsets.UTF_8.decode(content).toString()))
    }
    when(settings) {
        null -> LoadingHome(isSettingsLoaded)
        else -> {
            setSettingsLoaded(true)
            val state = rememberWindowState(
                placement = WindowPlacement.Floating,
                position = WindowPosition.Aligned(Alignment.Center),
                size = DpSize(settings.dimensions.width.dp, settings.dimensions.height.dp)
            )
            val viewFileMenu = remember { mutableStateOf(false) }
            val isFileChooserOpen = remember { mutableStateOf(false) }
            val viewSettings = remember { mutableStateOf(false) }
            Window(
                title = "Helium",
                state = state,
                onCloseRequest = ::exitApplication,
                // icon = helium(),
                //probably will switch to aurora integrated one day
                onPreviewKeyEvent = {
                    if(it.isCtrlPressed && it.key == Key.W) {
                        exitApplication()
                        true
                    } else false
                },
                resizable = true
            ) {
                    HeliumTheme {
                        Box(Modifier.fillMaxSize()) {
                            CodeInterface(settings)
                            if(isFileChooserOpen.value) {
                                FileDialog(
                                    ComposeWindow(),
                                    "Choose A File",
                                    listOf(""),
                                    onCloseRequest = {
                                        println(it)
                                        isFileChooserOpen.value = false
                                    }
                                )
                            }
                            //NewFile(settings, viewFileMenu, skin.value)
                            SettingsEditor(settings, viewSettings)

                    }
                }
            }
        }
    }
}


