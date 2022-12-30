package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import jetbrains

@Composable
fun CodeTextArea(
    initialText: TextFieldValue?,
    style: TextStyle = TextStyle(fontFamily = jetbrains()),
) {
    var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
    LineNumberList(lineTops, style)
    BoxWithConstraints {
        Column {
            //for now
            val textFieldValue = remember { mutableStateOf(initialText ?: TextFieldValue("")) }
            BasicTextField(
                value = textFieldValue.value,
                onValueChange = {
                    textFieldValue.value = it.copy(annotatedString = it.annotatedString)
                },
                onTextLayout = { result ->
                    lineTops = Array(result.lineCount) { result.getLineTop(it) }
                },
                textStyle = style,
            )
        }
    }

}