package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
    depth: Int,
    onClick: () -> Unit
) {
    Node(
        path,
        depth,
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
    depth: Int,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Node(
        path,
        depth,
        leadingIcon = leadingIcon,
        onClick = onClick
    )

}
@Composable
private fun Node(
    path: Path,
    depth: Int,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        contentColor = colors.onSurface,
    ) {
        Row(
            Modifier.clickable(onClick = onClick).fillMaxWidth().padding(start=depth.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            leadingIcon()
            Text(text = path.fileName.name, maxLines = 1, style = MaterialTheme.typography.labelMedium)
        }
    }
}
