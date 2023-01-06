import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.BoxWithTooltip
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
import components.buttons.icon.Helium
import components.buttons.icon.Iconify
import components.buttons.icon.Maximize
import components.buttons.text.Edit
import components.buttons.text.File
import components.drawers.FileNavDrawer
import components.windows.HeliumWindow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import structs.Settings
import structs.loadSettings
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.Optional
import org.jetbrains.skiko.GenericSkikoView
val json = Json {
    prettyPrint = true
    encodeDefaults = true
}
val testBorder = BorderStroke(1.dp, Color.Red)
val appBarHeight = 48.dp
val buttonSizes = (appBarHeight * 5) / 8
val pad = (appBarHeight - buttonSizes)
@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    //run blocking for now, idk how to asynchronously do it
    val coroutineScope = rememberCoroutineScope()
    val (settings, setSettings) = remember { mutableStateOf<Settings?>(null) }
    coroutineScope.launch {
        val buf = ByteBuffer.allocate(4096)
        loadSettings(buf).join()
        setSettings(json.decodeFromString(StandardCharsets.UTF_8.decode(buf.flip()).toString()))
    }
    when (settings) {
        null -> LoadingHome()
        else -> {
            val state = rememberWindowState(
                placement = WindowPlacement.Floating,
                position = WindowPosition.Aligned(Alignment.Center),
                size = DpSize(settings.dimensions.width.dp, settings.dimensions.height.dp)
            )
            HeliumWindow(
                title = "Helium",
                appBarHeight = appBarHeight,
                state = state,
                darkMode = true,
                onCloseRequest = ::exitApplication,
                dropDowns = {
                    Helium()
                    File()
                    Edit()
                },
                actions = {
                    Iconify()
                    Maximize()
                    ExitButton()
                },
                onPreviewKeyEvent = {
                    if (it.isCtrlPressed && it.key == Key.W) {
                        exitApplication()
                        true
                    } else false
                },
                resizable = true
            ) {
                val directoryChosen = remember { mutableStateOf<Optional<String>>(Optional.empty()) }
                MainCodeLayout(settings, it, directoryChosen.value)
                FileNavDrawer(it, directoryChosen)
            }
        }
    }
}


