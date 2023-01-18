package components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import jetbrains

@Composable
fun LineNumberList(
    scrollState: ScrollState,
    lineTops: Array<Float>,
    style: TextStyle = TextStyle(fontFamily = jetbrains()),
) {

    val density = LocalDensity.current
    Column(
        Modifier
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Box {
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