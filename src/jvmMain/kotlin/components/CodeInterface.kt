package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneScope
import org.jetbrains.compose.splitpane.SplitPaneState
import pad
import structs.Settings
import testBorder
import java.nio.file.Path
import java.util.*
import kotlin.io.path.ExperimentalPathApi

@OptIn(ExperimentalSplitPaneApi::class, ExperimentalPathApi::class)
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
    val selectedPath = remember { mutableStateOf<Optional<Path>>(Optional.empty()) }
    HorizontalSplitPane(
        Modifier.padding(start = pad + buttonSizes),
        splitPaneState = SplitPaneState(.8f, true),
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
            DividerLessAlpha(
                Modifier
                    .width(4.dp)
                    .fillMaxHeight(),
                alpha = .5f
            )
        }
        handle {
            DividerLessAlpha(
                Modifier
                    .width(10.dp)
                    .markAsHandle()
                    .fillMaxHeight(),
                alpha = 0.0f
            )
        }
    }
}