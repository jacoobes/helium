package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import jetbrains

@Composable
fun LineNumberList(
    lineTops: Array<Float>,
    style: TextStyle = TextStyle(fontFamily = jetbrains()),
) {
    val density = LocalDensity.current
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxHeight()
    ) {
        if (lineTops.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(horizontal = (2.5).dp)
            ) {
                lineTops.forEachIndexed { index, top ->
                    Text(
                        (index + 1).toString(),
                        style = style,
                        modifier = Modifier
                            .offset(y = with(density) { top.toDp() })
                    )

                }
            }
        }
    }
}