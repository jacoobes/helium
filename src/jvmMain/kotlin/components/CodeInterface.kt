package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import pad
import structs.Settings
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
        second {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(PaddingValues(15.dp))
            ) {
                optionalPath(
                    { SidePanel(rootPath = it, selectedPath=selectedPath) },
                    { Text("Open A Dir") }
                )
            }
        }
    }
}