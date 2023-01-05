package components.textarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import kotlinx.coroutines.Job
import structs.Code
import structs.deriveMonochrome


@Composable
fun TextArea(
    code: Code,
    style: TextStyle,
) {
    val theme = MaterialTheme.colorScheme.deriveMonochrome()

    val value = TextFieldValue(
            annotatedString = parseCodeAsAnnotatedString(
                parser = PrettifyParser(),
                theme = object : CodeTheme(theme) {},
                lang = code.lang ?: CodeLang.Default,
                code = "adsfasdfasdfasdjlf"
            )
        )

    Box(
        contentAlignment = Alignment.CenterEnd,
    ) {
        Box(
            Modifier.fillMaxSize(.90f),
        ) {
            BasicTextField(
                value = value,
                textStyle = style,
                onValueChange = {  },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.matchParentSize()
            )
        }
    }
}