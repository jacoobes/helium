package components.textarea

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import structs.Code
import structs.themes.DerivedMonochrome


@Composable
fun TextArea(
    code: Code,
    style: TextStyle,
    //onTextLayout: (TextLayoutResult) -> Unit,
) {
    // 'remember' caches across recompositions, saves creating a new parser every time a new code is loaded
    val theme = DerivedMonochrome(if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme())
    val parser = remember { PrettifyParser() }
    var value by remember(code) {
        mutableStateOf(
            TextFieldValue(
                annotatedString = parseCodeAsAnnotatedString(
                    parser = parser,
                    theme = theme,
                    lang = code.lang ?: CodeLang.Default,
                    code = code.content
                )
            )
        )
    }
    Box {
        Box(
            Modifier.fillMaxSize(),
        ) {
            BasicTextField(
                value = value,
                textStyle = style,
                onValueChange = {
                    value = it.copy(
                        annotatedString = parseCodeAsAnnotatedString(
                            parser = parser,
                            theme = theme,
                            lang = code.lang ?: CodeLang.Default,
                            code = it.text
                        )
                    )
                },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.matchParentSize(),
                //onTextLayout = onTextLayout,
            )
        }
    }
}