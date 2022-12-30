package components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import structs.DefaultHeliumTheme


@Composable
fun ApplicationScope.HeliumWindow(
    title: String,
    state: WindowState,
    onCloseRequest: () -> Unit = ::exitApplication,
    visible: Boolean = true,
    actions: @Composable (FrameWindowScope.() -> Unit) = {},
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    resizable: Boolean = true,
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable (FrameWindowScope.() -> Unit)
) {
    Window(
        title = title,
        state = state,
        onCloseRequest = onCloseRequest,
        visible = visible,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent,
        resizable = resizable,
        undecorated = true
    ) {
        DefaultHeliumTheme(darkMode) {
            Column(
                Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                HeliumAppBar(actions)
                content()
                BottomAppBar()
            }
        }
    }
}

@Composable
fun FrameWindowScope.HeliumAppBar(
    actions: @Composable FrameWindowScope.() -> Unit
) {
    WindowDraggableArea {
        Row(
            Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(25.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                actions()
            }
        }
    }
}

@Composable
fun FrameWindowScope.BottomAppBar() {
    WindowDraggableArea {
        Row(
            Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color.Red)
        ) {

        }
    }
}