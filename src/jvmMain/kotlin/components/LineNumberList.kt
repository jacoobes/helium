package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains

@Composable
fun LineNumberList(
    lineTops : Array<Float>,
    style : TextStyle = TextStyle(fontFamily = jetbrains()),
) {
    val density = LocalDensity.current
    Column(
        Modifier
            .fillMaxHeight()
    ) {
        if (lineTops.isNotEmpty()) {
            Box(modifier = Modifier.padding(horizontal = 6.dp)) {
                lineTops.forEachIndexed { index, top ->
                    Text(
                        (index+1).toString(),
                        style= style,
                        modifier = Modifier.offset(y = with(density) { top.toDp() })
                    )

                }
            }
        }
    }
}