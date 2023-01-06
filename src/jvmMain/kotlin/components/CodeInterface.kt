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
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.*
import pad
import structs.Settings
import java.awt.Cursor
import java.nio.file.Path
import java.util.*

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
    snackbarHostState: SnackbarHostState,
    directoryChosen: Optional<String>
) {
    /**
     * If path is present, run the iff composable
     * else, run the els composable
     */
    val optionalPath: @Composable (
        iff: @Composable (Path) -> Unit,
        els: @Composable (() -> Unit)?
    ) -> Unit = { iff, els ->
        if (directoryChosen.isPresent) {
            iff(Path.of(directoryChosen.get()))
        } else {
            els?.invoke()
        }
    }
    val hSplitPanelState = rememberSplitPaneState(.8f, true)
    val selectedPath = remember { mutableStateOf<Optional<Path>>(Optional.empty()) }
    HorizontalSplitPane(
        Modifier.padding(start = pad + buttonSizes),
        splitPaneState = hSplitPanelState,
    ) {
        first {
            Column {
                TextActions(snackbarHostState)
                optionalPath(
                    { MiddlePanel(selectedPath.value) },
                    null
                )
            }
        }
        panelSplitter()
        second {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(PaddingValues(15.dp))
            ) {
                optionalPath(
                    { SidePanel(rootPath = it, selectedPath = selectedPath) },
                    { Column(Modifier.fillMaxHeight()) { Text("Open a dir") } }
                )
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
                thickness = 4.dp
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