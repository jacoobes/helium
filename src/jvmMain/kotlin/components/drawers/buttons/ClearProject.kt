package components.drawers.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import java.nio.file.Path
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClearProject(
    selectedDirectory: MutableState<Optional<String>>,
    currentSelectPath: MutableState<Path?>
) {
    val enabled = remember(selectedDirectory.value) { selectedDirectory.value.isPresent }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledTonalIconButton(
            onClick = {
                selectedDirectory.value = Optional.empty()
                currentSelectPath.value = null
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