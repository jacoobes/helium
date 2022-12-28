package components

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import structs.Code


@Composable
fun MainCodingPanel(code: Code) {
    val themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
    CodeTextArea(
        TextFieldValue(
            annotatedString = parseCodeAsAnnotatedString(
                parser = remember { PrettifyParser() },
                theme = remember(themeState) { themeState.theme },
                lang = code.lang ?: CodeLang.Default,
                code = code.content
            )
        ),
        style = TextStyle(
            fontFamily = jetbrains(),
        )
    )
}