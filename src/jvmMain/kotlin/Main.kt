import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import components.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.twilightSkin
import org.pushingpixels.aurora.window.*
import structs.Settings
import structs.SettingsScope
import java.io.File
import java.nio.charset.StandardCharsets

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
            val skin = remember { mutableStateOf(twilightSkin()) }
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
                        skin.value,
                        listOf(
                            "Save" to { println("saved") },
                            "Open File" to { isFileChooserOpen.value = true },
                            "Open Directory" to { println("open") },
                            "Exit Helium" to ::exitApplication
                        )
                    )
                }
                NewFile(settings, viewFileMenu, skin)
                SettingsEditor(settings, skin.value, viewSettings)
            }
        }
    }

}


@Composable
fun AuroraWindowScope.HeliumApp(settings: Settings) {
    BreadcrumbContent()
    AuroraDecorationArea(DecorationAreaType.ControlPane) {
        Row {
            LeftSidePanel(settings)
            val parser = remember { PrettifyParser() }
            val lang = CodeLang.Kotlin
            val themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
            val theme = remember(themeState) { themeState.theme }
            val code = """
                fun main() {
                    println("string args")  
                }
            """.trimIndent()
            TextArea(
                settings,
                TextFieldValue(
                    annotatedString = parseCodeAsAnnotatedString(
                        parser = parser,
                        theme = theme,
                        lang = lang,
                        code = code
                    )
                ),
                style = TextStyle(
                    fontFamily = jetbrains()
                ))
            }
    }
}


