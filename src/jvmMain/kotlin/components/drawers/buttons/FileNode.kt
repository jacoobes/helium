package components.drawers.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource

@Composable
fun FileNode(
    onClick: () -> Unit = {},
) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(painterResource("scalable/folder.svg"), contentDescription = null)
        }
}
