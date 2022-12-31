package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import structs.DefaultHeliumTheme
import testBorder

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
            Scaffold(
                topBar = {
                    HeliumAppBar(
                        height = appBarHeight,
                        actions = actions,
                        titlePaneButtons = dropDowns
                    )
                },
                bottomBar = {
                    BottomAppBar()
                },
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                Box(
                    Modifier.padding(
                        top=it.calculateTopPadding()
                    )
                ) {
                    content()
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
    WindowDraggableArea {
        Row(
            Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(height),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
            ) {
                titlePaneButtons()
            }
            Row(
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