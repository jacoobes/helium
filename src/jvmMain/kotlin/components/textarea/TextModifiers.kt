package components.textarea

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import appBarHeight
import buttonSizes
import kotlinx.coroutines.launch
import testBorder


@Composable
fun TextActions(
    snackbarHostState: SnackbarHostState
) {

    Row(
        Modifier.height(ButtonDefaults.MinHeight).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AutosaveAction(false, snackbarHostState)
        //maybe custom input chip
        val border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        FilledTonalButton(
            onClick = {},
            content = { Text("Commit") },
            border = border,
            //half the material spec padding
        )
        TextButton(
            onClick = {},
            content = { Text("Save") },
            //half the material spec padding
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