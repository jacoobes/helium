package components.drawers.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch
import structs.FileChooser
import java.util.*

@Composable
fun DirectoryChooser(
    enabled : Boolean = true,
    selectedDirectory : MutableState<Optional<String>>
) {
    val scope = rememberCoroutineScope()
    IconButton(
        onClick = {
            scope.launch {
                selectedDirectory.value = Optional.ofNullable(FileChooser.chooseDirectory())
            }
        },
        enabled = enabled
    ) {
        Icon(
            painterResource("scalable/folder_open_24.svg"),
            contentDescription = null,
        )
    }
}