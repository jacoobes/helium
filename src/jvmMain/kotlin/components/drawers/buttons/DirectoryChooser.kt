package components.drawers.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.rememberCursorPositionProvider
import components.Tooltip
import kotlinx.coroutines.launch
import structs.FileChooser
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DirectoryChooser(
    enabled : Boolean = true,
    selectedDirectory : MutableState<Optional<String>>
) {
    val scope = rememberCoroutineScope()
    val showTooltip = remember { mutableStateOf(false) }
    Box {
        IconButton(
            modifier = Modifier
                .onPointerEvent(PointerEventType.Enter) { showTooltip.value = true }
                .onPointerEvent(PointerEventType.Exit) { showTooltip.value = false },
            onClick = {
                scope.launch {
                    val chosenDir = FileChooser.chooseDirectory()
                    val shouldUpdate = chosenDir != null
                    if(shouldUpdate) {
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
        Tooltip(
            showTooltip,
            text = "Open directory",
            offset = DpOffset(0.dp, (-15).dp)
        )
    }

}