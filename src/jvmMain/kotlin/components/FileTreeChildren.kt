package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.nio.file.Path
import kotlin.io.path.name


@Composable
fun FileChild(
    path: Path,
    onClick: () -> Unit
) {
    Node(
        path,
        leadingIcon = {
            Icon(
                painterResource("scalable/folder.svg"),
                null,
                Modifier.size(20.dp)
                )
        },
        onClick = onClick
    )
}
@Composable
fun DirectoryChild(
    path: Path,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Node(
        path,
        leadingIcon = leadingIcon,
        onClick = onClick
    )

}
@Composable
private fun Node(
    path: Path,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        contentColor = colors.onSurface,
    ) {
        Row(
            Modifier.clickable(onClick = onClick).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            leadingIcon()
            Text(text = path.fileName.name, maxLines = 1, style = MaterialTheme.typography.labelMedium)
        }
    }
}
