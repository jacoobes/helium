package components.textarea
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import structs.Code
import java.nio.file.Path


@Composable
fun TextArea(
    p: Path,
    code: Code,
    codeTheme: CodeTheme,
    style: TextStyle,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val parser = remember { PrettifyParser() }
    val focusRequester = remember { FocusRequester() }
    var value by remember(p) {
        mutableStateOf(
            TextFieldValue(
                annotatedString = parseCodeAsAnnotatedString(
                    parser = parser,
                    theme = codeTheme,
                    lang = code.lang ?: CodeLang.Default,
                    code = code.content
                )
            )
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BasicTextField(
        value = value,
        textStyle = style,
        modifier = Modifier
            .focusRequester(focusRequester),
        onValueChange = {
            value = it.copy(
                annotatedString = parseCodeAsAnnotatedString(
                    parser = parser,
                    theme = codeTheme,
                    lang = code.lang ?: CodeLang.Default,
                    code = it.text
                )
            )
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        onTextLayout = onTextLayout
    )
}