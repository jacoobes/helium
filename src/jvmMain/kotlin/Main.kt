import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import components.*
import components.iconbuttons.ExitButton
import components.iconbuttons.Iconify
import components.iconbuttons.Maximize
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import structs.Settings
import structs.loadSettingsAsync
import java.nio.charset.StandardCharsets

val json = Json {
    prettyPrint = true
    encodeDefaults = true
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    //run blocking for now, idk how to asynchronously do it
    val coroutineScope = rememberCoroutineScope()
    val (settings, setSettings) = remember { mutableStateOf<Settings?>(null) }
    coroutineScope.launch {
        val content = loadSettingsAsync().await().flip()
        setSettings(json.decodeFromString(StandardCharsets.UTF_8.decode(content).toString()))
    }
    when (settings) {
        null -> LoadingHome()
        else -> {
            val state = rememberWindowState(
                placement = WindowPlacement.Floating,
                position = WindowPosition.Aligned(Alignment.Center),
                size = DpSize(settings.dimensions.width.dp, settings.dimensions.height.dp)
            )
            val isFileChooserOpen = remember { mutableStateOf(false) }
            val viewSettings = remember { mutableStateOf(false) }
            HeliumWindow(
                title = "Helium",
                state = state,
                onCloseRequest = ::exitApplication,
                actions = {
                    val buttonPadding = PaddingValues(start = 5.dp, end = 5.dp)
                    Iconify(buttonPadding)
                    Maximize(buttonPadding)
                    ExitButton(buttonPadding)
                },
                // icon = helium(),
                //probably will switch to aurora integrated one day
                onPreviewKeyEvent = {
                    if (it.isCtrlPressed && it.key == Key.W) {
                        exitApplication()
                        true
                    } else false
                },
                resizable = true
            ) {
                CodeInterface(settings)
                if (isFileChooserOpen.value) {
                    FileDialog(
                        title = "Choose A File",
                        allowedExtensions = listOf(""),
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


