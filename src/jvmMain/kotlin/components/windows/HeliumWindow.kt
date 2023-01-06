package components.windows

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import components.DividerLessAlpha
import structs.themes.DefaultHeliumTheme

/**
 * A basic window that comes with the jetpack compose Scaffold layout
 * @param dropDowns buttons that can do something that are placed at the left most of the window title
 * @param actions buttons that interact with the window state (exit, minimize, maximize)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScope.HeliumWindow(
    title: String,
    appBarHeight : Dp = 25.dp,
    state: WindowState,
    onCloseRequest: () -> Unit = ::exitApplication,
    visible: Boolean = true,
    dropDowns: @Composable FrameWindowScope.() -> Unit = {},
    actions: @Composable (FrameWindowScope.() -> Unit) = {},
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    resizable: Boolean = true,
    darkMode: Boolean = isSystemInDarkTheme(),
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
        DefaultHeliumTheme(darkMode) {
            Scaffold(
                topBar = {
                    HeliumAppBar(
                        height = appBarHeight,
                        actions = actions,
                        titlePaneButtons = dropDowns
                    )
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = { BottomAppBar() },
            ) {
                Box(
                    Modifier.padding(
                        top=it.calculateTopPadding()
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
    height: Dp = 25.dp,
    titlePaneButtons: @Composable FrameWindowScope.() -> Unit,
    actions: @Composable FrameWindowScope.() -> Unit
) {
    val buttonSizes = (height * 5) / 8
    val pad = (height - buttonSizes)/2
        Column {
            WindowDraggableArea {
                Row(
                    Modifier
                        .height(buttonSizes)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        Modifier
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(pad,Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        titlePaneButtons()
                    }
                    Row(
                        Modifier
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(pad, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        actions()
                    }
                }
            }
            DividerLessAlpha(alpha = .5f)
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