package components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import components.textarea.TextArea
import jetbrains
import structs.Code
import structs.HeliumThemes
import structs.deriveMonochrome


@Composable
fun MainCodingPanel(code: Code) {
    val theme = MaterialTheme.colorScheme.deriveMonochrome()
    val textFieldValue = remember {
        TextFieldValue(
            annotatedString = parseCodeAsAnnotatedString(
                parser = PrettifyParser(),
                theme = object : CodeTheme(theme) {},
                lang = code.lang ?: CodeLang.Default,
                code = code.content
            )
        )
    }
    TextArea(
        textFieldValue,
        style = TextStyle(
            fontFamily = jetbrains(),
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}