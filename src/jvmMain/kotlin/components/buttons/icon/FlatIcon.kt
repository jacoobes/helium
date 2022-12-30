package components.buttons.icon

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FlatIcon(
    icon: ImageVector,
    scheme: Pair<Color, Color>,
    contentDescription: String? = null
) {
    Icon(
        icon,
        contentDescription = contentDescription,
        modifier = Modifier.background(scheme.first),
        tint = scheme.second
    )
}


@Composable
fun FlatIcon(
    icon: Painter,
    scheme: Pair<Color, Color>,
    contentDescription: String? = null
) {
    Icon(
        icon,
        contentDescription = contentDescription,
        modifier = Modifier.background(scheme.first),
        tint = scheme.second
    )
}