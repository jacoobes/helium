package components.textarea

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import testBorder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextActions(snackbarHostState: SnackbarHostState) {

    Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        AutosaveAction(false, snackbarHostState)
        //maybe custom input chip
        InputChip(
            label = { Text("Font") },
            onClick = {},
        )
        AssistChip(
            onClick = {},
            label = { Text("Shit") },
        )
        AssistChip(
            onClick = {},
            label = { Text("Commit") }
        )
    }
}

@Composable
fun AutosaveAction(isOn: Boolean, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    val isOnState = remember { mutableStateOf(isOn) }
    Switch(
        checked = isOnState.value,
        onCheckedChange = {
            isOnState.value = it
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Autosave ${if (isOnState.value) "on" else "off"}",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )
            }
        },
    )
}