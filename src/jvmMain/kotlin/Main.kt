import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.*
import structs.Settings
import structs.loadSettingsAsync
import java.nio.charset.StandardCharsets

val json = Json {
    prettyPrint = true
    encodeDefaults = true
}
@OptIn(ExperimentalComposeUiApi::class)
fun main() = auroraApplication {
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
            val skin = remember { mutableStateOf(nightShadeSkin()) }
            val viewFileMenu = remember { mutableStateOf(false) }
            val isFileChooserOpen = remember { mutableStateOf(false) }
            val viewSettings = remember { mutableStateOf(false) }
            AuroraWindow(
                title = "Helium",
                state = state,
                skin = skin.value,
                onCloseRequest = ::exitApplication,
                windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
                // icon = helium(),
                //probably will switch to aurora integrated one day
                menuCommands = CommandGroup(
                    commands = listOf(
                        Command("File",
                            action =  { viewFileMenu.value = true }),
                        Command("Settings", action = { viewSettings.value = true }),
                    )
                ),
                onPreviewKeyEvent = {
                    if(it.isCtrlPressed && it.key == Key.W) {
                        exitApplication()
                        true
                    } else false
                },
                resizable = true
            ) {
                Box(Modifier.fillMaxSize()) {
                    CodeInterface(settings, skin)
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
//                    if(viewFileMenu.value) {
//                        this@Box.CommandMenu(
//                            viewFileMenu,
//                            listOf(
//                                "Save" to { println("saved") },
//                                "Open File" to { isFileChooserOpen.value = true },
//                                "Open Directory" to { println("open") },
//                                "Exit Helium" to ::exitApplication
//                            )
//                        )
//                    }
                    //NewFile(settings, viewFileMenu, skin.value)
                    SettingsEditor(settings, skin, viewSettings)
                }
            }
        }
    }
}


