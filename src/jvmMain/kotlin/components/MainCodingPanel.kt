package components

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import jetbrains
import structs.Code
import structs.HeliumThemes


@Composable
fun MainCodingPanel(code: Code) {
    val textFieldValue = remember {
        TextFieldValue(
            annotatedString = parseCodeAsAnnotatedString(
                parser = PrettifyParser(),
                theme = HeliumThemes.NightShadeMonoChrome(),
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