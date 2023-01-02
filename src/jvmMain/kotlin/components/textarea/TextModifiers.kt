package components.textarea

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextActions(snackbarHostState: SnackbarHostState) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
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

//TODO: update switch color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutosaveAction(isOn: Boolean, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    val isOnState = remember { mutableStateOf(isOn) }
    Switch(
        checked = isOnState.value,
        onCheckedChange = {
            isOnState.value = it
            scope.launch {
                snackbarHostState.showSnackbar(message = "Autosave on")
            }
        },
    )
}