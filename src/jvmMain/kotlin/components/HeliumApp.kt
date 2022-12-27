package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import org.pushingpixels.aurora.component.model.CommandPanelContentModel
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Code
import structs.Settings

@Composable
fun AuroraWindowScope.HeliumApp(settings: Settings, skin: MutableState<AuroraSkinDefinition>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val commandPanelContentModel = remember { mutableStateOf<CommandPanelContentModel?>(null) }
        BreadcrumbContent(commandPanelContentModel)
        val currentCode by remember {
            mutableStateOf(
                Code("""
                        fun main() {
                            println("string args")  
                        }
                    """.trimIndent(), "kt"
                )
            )
        }
        Row(modifier = Modifier.fillMaxHeight(0.95f)) {
            LeftSidePanel(commandPanelContentModel)
            val themeState by remember { mutableStateOf(CodeThemeType.Monokai) }

            CodeTextArea(
                TextFieldValue(
                    annotatedString = parseCodeAsAnnotatedString(
                        parser = remember { PrettifyParser() },
                        theme = remember(themeState) { themeState.theme },
                        lang = currentCode.lang ?: CodeLang.Default,
                        code = currentCode.content
                    )
                ),
                style = TextStyle(
                    fontFamily = jetbrains()
                )
            )
        }
        Row {
            AuroraDecorationArea(DecorationAreaType.Footer) {
                Footer(skin, currentCode)
            }
        }

    }
}