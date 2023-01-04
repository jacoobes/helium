 package components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
fun ApplicationScope.LoadingHome() {
    Window(
        title = "poo",
        state = WindowState(
            WindowPlacement.Floating,
            false,
            WindowPosition.Aligned(Alignment.Center),
            DpSize(500.dp, 250.dp)
        ),
        onCloseRequest = ::exitApplication
    ) {
        Text("LOADING")
    }
}