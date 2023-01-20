package components.textarea

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.textarea.actions.SaveCode
import kotlinx.coroutines.launch
import structs.Code
import java.nio.file.Path
import java.util.*


@Composable
fun TextActions(
    snackbarHostState: SnackbarHostState,
    code: Code,
) {
    val scope = rememberCoroutineScope()
    Row(
        Modifier.height(ButtonDefaults.MinHeight).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        //Maybe move autosave somewhere else
        AutosaveAction(false, snackbarHostState)
        val border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        SaveCode(snackbarHostState, code)
        FilledTonalButton(
            onClick = {},
            content = { Text("Commit") },
            border = border,
        )
        TextButton(
            content = { Text("Font", style = MaterialTheme.typography.labelSmall) },
            border = border,
            onClick = {},
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