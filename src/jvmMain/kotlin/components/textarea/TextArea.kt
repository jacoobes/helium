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
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import structs.Code


@Composable
fun TextArea(
    code: Code,
    codeTheme: CodeTheme,
    style: TextStyle,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val parser = remember { PrettifyParser() }
    val focusRequester = remember { FocusRequester() }
    var value by remember(code.path) {
        mutableStateOf(
            TextFieldValue(
                annotatedString = code.annotatedString(
                    parser,
                    codeTheme,
                    conten = code.content
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
                annotatedString = code.annotatedString(
                    parser = parser,
                    codeTheme = codeTheme,
                    conten = it.text
                )
            )
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        onTextLayout = onTextLayout,
    )
}