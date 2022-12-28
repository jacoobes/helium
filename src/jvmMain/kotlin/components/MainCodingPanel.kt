package components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import structs.Code
import structs.HeliumTheme


@Composable
fun MainCodingPanel(skin: MutableState<AuroraSkinDefinition>, code: Code) {
    val theme by remember(skin) {  mutableStateOf(HeliumTheme(skin.value, null).createThemeBasedOffSkin(Color.Red)) }
    val parser by remember(skin) { mutableStateOf(PrettifyParser()) }
    CodeTextArea(
        TextFieldValue(
            annotatedString = parseCodeAsAnnotatedString(
                parser = parser,
                theme = theme,
                lang = code.lang ?: CodeLang.Default,
                code = code.content
            )
        ),
        style = TextStyle(
            fontFamily = jetbrains(),
        )
    )
}