package components

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import structs.Code
import structs.HeliumThemes


@Composable
fun MainCodingPanel(skin: MutableState<AuroraSkinDefinition>, code: Code) {
    val theme by remember(skin) {  mutableStateOf(HeliumThemes.NightShadeMonoChrome()) }
    val parser by remember(skin) { mutableStateOf(PrettifyParser()) }
    val textFieldValue = remember(skin) {
        TextFieldValue(
        annotatedString = parseCodeAsAnnotatedString(
            parser = parser,
            theme = theme,
            lang = code.lang ?: CodeLang.Default,
            code = code.content
        )
        )
    }
    CodeTextArea(
        textFieldValue,
        style = TextStyle(
            fontFamily = jetbrains(),
        )
    )
}