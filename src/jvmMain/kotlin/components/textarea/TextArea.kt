package components.textarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun TextArea(
    value: TextFieldValue,
    style: TextStyle
) {
    Box(
        contentAlignment = Alignment.CenterEnd
    ) {

        Box(
            Modifier.fillMaxSize(.90f)
        ) {
            BasicTextField(
                value = value,
                textStyle = style,
                onValueChange = {},
                modifier = Modifier.matchParentSize()
            )
        }

    }
}