package components.buttons.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FlatIcon(
    icon: ImageVector,
    contentDescription: String? = null
) {
    Icon(
        icon,
        contentDescription = contentDescription,
    )
}


@Composable
fun FlatIcon(
    icon: Painter,
    contentDescription: String? = null
) {
    Icon(
        icon,
        contentDescription = contentDescription,
    )
}