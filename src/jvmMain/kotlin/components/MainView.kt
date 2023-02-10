package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.lazytree.NoFiles
import components.textarea.TabView
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.*
import pad
import structs.Code
import structs.CodeEditorState
import structs.Editor
import java.awt.Cursor
import java.nio.file.Path
import java.util.*
@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun MainView(
    snackbarHostState: SnackbarHostState,
    editorState
    : CodeEditorState,
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
            if (editorState.directoryChosen.value.isPresent) {
                Column(Modifier.fillMaxWidth()) {
                    if(editorState.currentSelectPath.value != null) {
                        val selectedTabIndex = remember { mutableStateOf(0) }
                        val code = Code(editorState.currentSelectPath.value!!)
                        val editor = Editor(code)
                        if(!editorState.editors.value.contains(editor)) {
                            editorState.editors.value.add(editor)
                        }
                        selectedTabIndex.value = editorState.editors.value.indexOf(editor)
                        TabView(selectedTabIndex, editorState.editors.value)
                        TextActions(snackbarHostState, code)
                        CodeView(
                            mode = editorState.themeMode,
                            theme = editorState.currentTheme,
                            code
                        )
                    }
                }
            }
        }
        panelSplitter()

        second(
            minSize = 50.dp
        ) {
            if (editorState.directoryChosen.value.isPresent) {
                SidePanel(
                    rootPath = Path.of(editorState.directoryChosen.value.get()),
                    selectedPath = editorState.currentSelectPath
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