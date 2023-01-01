package components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.jetbrains.skiko.SkiaLayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileNode(
    enabled: Boolean = false,
    onClick: () -> Unit = {},
) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(painterResource("scalable/folder.svg"), contentDescription = null)
        }
}

@Composable
fun HalfIconButton(
    onClick : () -> Unit = {},
    enabled: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content : @Composable () -> Unit,
) {
    Box(
        Modifier
            .clickable(
                onClick = onClick,
                role = Role.Button,
                enabled = enabled,

            ),
    ) {

        content()
    }
}