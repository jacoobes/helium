import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.*
import components.buttons.icon.ExitButton
import components.buttons.icon.Iconify
import components.buttons.icon.Maximize
import components.buttons.text.File
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
val testBorder = BorderStroke(1.dp, Color.Red)

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
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
            val titlePanePaddingValues = PaddingValues(start = 5.dp, end = 5.dp)
            HeliumWindow(
                title = "Helium",
                appBarHeight = 30.dp,
                state = state,
                onCloseRequest = ::exitApplication,
                dropDowns = {
                    //Helium()
                    File(titlePanePaddingValues)
                },
                actions = {
                    Iconify(titlePanePaddingValues)
                    Maximize(titlePanePaddingValues)
                    ExitButton(titlePanePaddingValues)
                },
                onPreviewKeyEvent = {
                    if (it.isCtrlPressed && it.key == Key.W) {
                        exitApplication()
                        true
                    } else false
                },
                resizable = true
            ) {

                MainCodeLayout(settings)
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


