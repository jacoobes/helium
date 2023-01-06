package components.drawers.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch
import structs.FileChooser
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectoryChooser(
    enabled: Boolean = true,
    selectedDirectory: MutableState<Optional<String>>
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val scope = rememberCoroutineScope()
        FilledTonalIconButton(
            onClick = {
                scope.launch {
                    val chosenDir = FileChooser.chooseDirectory()
                    val shouldUpdate = chosenDir != null
                    if (shouldUpdate) {
                        selectedDirectory.value = Optional.ofNullable(chosenDir)
                    }
                }
            },
            enabled = enabled
        ) {
            Icon(
                painterResource("scalable/folder_open_24.svg"),
                contentDescription = null,
            )
        }
        Text("Open", style = MaterialTheme.typography.titleSmall)

    }

}