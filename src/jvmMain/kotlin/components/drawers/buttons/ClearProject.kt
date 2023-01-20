package components.drawers.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import java.util.*

@Composable
fun ClearProject(
    selectedDirectory: MutableState<Optional<String>>
) {
    val enabled = remember(selectedDirectory.value) { selectedDirectory.value.isPresent }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = {
                selectedDirectory.value = Optional.empty()
            },
            enabled = enabled
        ) {
            Icon(
                painterResource("scalable/delete24.svg"),
                contentDescription = null,
            )
        }
        Text("Clear", style = MaterialTheme.typography.titleSmall)
    }
}