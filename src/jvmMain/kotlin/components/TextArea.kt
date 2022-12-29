package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains
import structs.Settings

@Composable
fun CodeTextArea(
    initialText: TextFieldValue?,
    style : TextStyle = TextStyle(fontFamily = jetbrains()),
) {
        var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
        LineNumberList(lineTops, style)
        Column(
            Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background)
        ) {
            //for now
            val textFieldValue = remember { mutableStateOf(initialText ?: TextFieldValue("")) }
            BasicTextField(
                value = textFieldValue.value,
                onValueChange = {
                    textFieldValue.value = it.copy(annotatedString = it.annotatedString) },
                onTextLayout = { result ->
                    lineTops = Array(result.lineCount) { result.getLineTop(it) }
                },
                textStyle = style,
                modifier = Modifier.fillMaxSize()
            )
        }
}