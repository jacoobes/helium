package components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun FlatText(
    text : String,
    scheme : Pair<Color, Color>,
) {

    Text(
        text,
        modifier = Modifier.background(scheme.first),
        fontSize = 14.sp,
        color = scheme.second,
    )
}