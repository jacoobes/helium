package components.windows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import buttonSizes
import structs.ThemeMode
import structs.themes.HeliumTheme
import structs.themes.UseHeliumTheme

/**
 * A basic window that comes with the jetpack compose Scaffold layout
 * @param dropDowns buttons that can do something that are placed at the left most of the window title
 * @param actions buttons that interact with the window state (exit, minimize, maximize)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScope.HeliumWindow(
    title: String,
    state: WindowState,
    onCloseRequest: () -> Unit = ::exitApplication,
    visible: Boolean = true,
    dropDowns: @Composable FrameWindowScope.() -> Unit = {},
    actions: @Composable (FrameWindowScope.() -> Unit) = {},
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    resizable: Boolean = true,
    theme: HeliumTheme,
    mode: ThemeMode,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (FrameWindowScope.(hostState: SnackbarHostState) -> Unit)
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
        UseHeliumTheme(theme, mode) {
            Scaffold(
                topBar = {
                    HeliumAppBar(
                        actions = actions,
                        titlePaneButtons = dropDowns
                    )
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = { BottomAppBar() },
            ) {
                Box(
                    Modifier.padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    )
                ) {
                    content(snackbarHostState)
                }
            }
        }
    }
}

@Composable
fun FrameWindowScope.HeliumAppBar(
    titlePaneButtons: @Composable FrameWindowScope.() -> Unit,
    actions: @Composable FrameWindowScope.() -> Unit
) {
    Column {
        WindowDraggableArea {
            Row(
                Modifier
                    .height(buttonSizes)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                titlePaneButtons()
                Spacer(Modifier.weight(1f))
                actions()
            }
        }
    }
}

@Composable
fun FrameWindowScope.BottomAppBar() {
    WindowDraggableArea {
        Surface(
            tonalElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("BottomBar!")
            }
        }
    }
}