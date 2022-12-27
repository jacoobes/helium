import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import components.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.theming.twilightSkin
import org.pushingpixels.aurora.window.*
import structs.Settings
import structs.SettingsScope
import java.io.File
import java.nio.charset.StandardCharsets
import javax.swing.filechooser.FileSystemView

val json = Json {
    prettyPrint = true
    encodeDefaults = true
}
fun main() = auroraApplication {
    //run blocking for now, idk how to asynchronously do it
    val coroutineScope = rememberCoroutineScope()
    val (settings, setSettings) = remember { mutableStateOf<Settings?>(null) }
    val (isSettingsLoaded, setSettingsLoaded) = remember { mutableStateOf(false) }
    coroutineScope.launch {
        val content = SettingsScope.loadSettingsAsync().await().flip()
        setSettings(json.decodeFromString(StandardCharsets.UTF_8.decode(content).toString()))
    }
    when(settings) {
        null -> {
            LoadingHome(isSettingsLoaded)
        }
        else -> {
            setSettingsLoaded(true)
            val state = rememberWindowState(
                placement = WindowPlacement.Floating,
                position = WindowPosition.Aligned(Alignment.Center),
                size = DpSize(settings.dimensions.width.dp, settings.dimensions.height.dp)
            )
            val (skin , setSkin) = remember { mutableStateOf(twilightSkin()) }
            val viewFileMenu = remember { mutableStateOf(false) }
            val isFileChooserOpen = remember { mutableStateOf(false) }
            val viewSettings = remember { mutableStateOf(false) }
            AuroraWindow(
                title = "Helium",
                state = state,
                skin = skin,
                onCloseRequest = ::exitApplication,
                windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
                // icon = helium(),
                menuCommands = CommandGroup(
                    commands = listOf(
                        Command("File", action =  { viewFileMenu.value = true }),
                        Command("Settings", action = { viewSettings.value = true }),
                    )
                ),
                resizable = true
            ) {
                HeliumApp(settings)
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
                if(viewFileMenu.value) {
                    CommandMenu(
                        settings,
                        viewFileMenu,
                        skin,
                        listOf(
                            "Save" to { println("saved") },
                            "Open File" to { isFileChooserOpen.value = true },
                            "Open Directory" to { println("open") },
                            "Exit Helium" to ::exitApplication
                        )
                    )
                }
                NewFile(settings, viewFileMenu, skin)
                SettingsEditor(settings, skin, viewSettings)
            }
        }
    }
}


