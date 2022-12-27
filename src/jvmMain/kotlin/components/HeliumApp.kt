package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Settings

@Composable
fun AuroraWindowScope.HeliumApp(settings: Settings) {
    BreadcrumbContent()
    Column {
        Row(modifier = Modifier.fillMaxHeight(0.95f)) {
            AuroraDecorationArea(DecorationAreaType.ControlPane) {
                LeftSidePanel(settings)
            }
            val themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
            CodeTextArea(
                settings,
                TextFieldValue(
                    annotatedString = parseCodeAsAnnotatedString(
                        parser = remember { PrettifyParser() },
                        theme = remember(themeState) { themeState.theme },
                        lang = CodeLang.Default,
                        code = """
                          fun main() {
                              println("string args")  
                          }
                      """.trimIndent()
                    )
                ),
                style = TextStyle(
                    fontFamily = jetbrains()
                )
            )
        }
        Row {
            AuroraDecorationArea(DecorationAreaType.Footer) {
                Footer()
            }
        }
    }


}