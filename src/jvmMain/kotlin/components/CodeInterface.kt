package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.lazytree.NoFiles
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.*
import pad
import structs.Code
import structs.DrawerButtonsState
import java.awt.Cursor
import java.nio.file.Path
import java.util.*
@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FrameWindowScope.MainView(
    snackbarHostState: SnackbarHostState,
    drawerButtonsState: DrawerButtonsState,
) {
    val hSplitPanelState = rememberSplitPaneState(.8f, true)
    val padding = PaddingValues(start = pad + buttonSizes + 10.dp)
    // TODO:
    // This will be changed to a more specialize horizontal split pane because the current one causes too many issues
    HorizontalSplitPane(
        Modifier.padding(padding),
        splitPaneState = hSplitPanelState,
    ) {
        first(
            minSize = 100.dp
        ) {
            if (drawerButtonsState.directoryChosen.value.isPresent) {
                Column(Modifier.fillMaxWidth()) {
                    if(drawerButtonsState.currentSelectPath.value != null) {
                        val code = Code(drawerButtonsState.currentSelectPath.value!!)
                        TextActions(snackbarHostState, code)
                        MiddlePanel(
                            mode = drawerButtonsState.themeMode,
                            theme = drawerButtonsState.currentTheme,
                            code
                        )
                    } else {
                        Text("Open a file from the side panel")
                    }
                }
            }
        }
        panelSplitter()

        second(
            minSize = 50.dp
        ) {
            if (drawerButtonsState.directoryChosen.value.isPresent) {
                SidePanel(
                    rootPath = Path.of(drawerButtonsState.directoryChosen.value.get()),
                    selectedPath = drawerButtonsState.currentSelectPath
                )
            } else {
                NoFiles()
            }
        }
    }
}


@OptIn(ExperimentalSplitPaneApi::class)
fun SplitPaneScope.panelSplitter() {
    splitter {
        visiblePart {
            VerticalDividerLessAlpha(
                modifier = Modifier.clip(CircleShape),
                thickness = 1.dp
            )
        }
        handle {
            VerticalDividerLessAlpha(
                Modifier
                    .markAsHandle()
                    .cursorForHorizontalResize(),
                alpha = 0.0f,
                thickness = 10.dp
            )
        }
    }
}

private fun Modifier.cursorForHorizontalResize(): Modifier =
    pointerHoverIcon(PointerIcon(Cursor(Cursor.E_RESIZE_CURSOR)))