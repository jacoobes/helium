package components.textarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun TextArea(
    value: MutableState<TextFieldValue>,
    style: TextStyle,
) {
    Box(
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            Modifier.fillMaxSize(.90f)
        ) {
            BasicTextField(
                value = value.value,
                textStyle = style,
                onValueChange = { value.value = it },
                modifier = Modifier.matchParentSize()
            )
        }

    }
}