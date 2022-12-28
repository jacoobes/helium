package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import org.pushingpixels.aurora.component.model.CommandPanelContentModel
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Code
import structs.Settings

@Composable
fun AuroraWindowScope.CodeInterface(
    settings: Settings,
    skin: MutableState<AuroraSkinDefinition>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val commandPanelContentModel = remember { mutableStateOf<CommandPanelContentModel?>(null) }
        BreadcrumbContent(commandPanelContentModel)
        val currentCode by remember {
            mutableStateOf(
                Code("""
                        // a comment
                        fun main() {
                            val t : Int = ""
                            println("string args")  
                        }
                    """.trimIndent(), "kt"
                )
            )
        }
        Row(modifier = Modifier.fillMaxHeight(0.95f)) {
            Column(
                modifier = Modifier
                    //for now, 15% of the max width
                    .fillMaxWidth(.15f)
                    .fillMaxHeight()
            ) {
                LeftPanelCommands()
                LeftSidePanel(commandPanelContentModel)
            }
            MainCodingPanel(skin, currentCode)
        }
        Row {
            AuroraDecorationArea(DecorationAreaType.Footer) {
                Footer(skin, currentCode)
            }
        }

    }
}