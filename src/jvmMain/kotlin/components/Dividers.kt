package components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun DividerLessAlpha(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    thickness: Dp = 1.0.dp,
    alpha : Float
) {
    Divider(modifier, color=color.copy(alpha = alpha), thickness = thickness)
}
@Composable
fun VerticalDividerLessAlpha(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    thickness: Dp = 1.0.dp,
    alpha : Float = .5f
) {
    Divider(modifier.then(
        Modifier.fillMaxHeight().width(thickness)),
        color = color.copy(alpha = alpha),
        thickness = thickness
    )
}