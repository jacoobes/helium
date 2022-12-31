package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileNode(
    enabled : Boolean = false,
    onClick : () -> Unit = {},
) {
    ElevatedCard(
        modifier = Modifier
            .padding(Dp.Hairline)
            .rotate(90f)
            .clickable(
                onClick = onClick,
                enabled = enabled
            ),
        shape = RectangleShape,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(painterResource("scalable/folder.svg"), contentDescription = null)
        }
    }
}