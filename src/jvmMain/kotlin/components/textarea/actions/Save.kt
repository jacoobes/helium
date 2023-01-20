package components.textarea.actions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import structs.Code

@Composable
fun SaveCode(
    snackbarHostState: SnackbarHostState,
    code: Code
) {
    val scope = rememberCoroutineScope()
    TextButton(
        onClick = {
            scope.launch {
                code.save()
                snackbarHostState.showSnackbar(
                    message = "Saved",
                    duration = SnackbarDuration.Short
                )
            }
        },
        content = { Text("Save") },
    )
}