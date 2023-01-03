package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
