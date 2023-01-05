package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.textarea.TextActions
import kotlinx.coroutines.launch
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import pad
import structs.DirectoryNode
import structs.FileNode
import structs.Settings
import structs.TreeNode
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.fileVisitor

@OptIn(ExperimentalSplitPaneApi::class, ExperimentalPathApi::class)
@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
    snackbarHostState: SnackbarHostState,
    directoryChosen: Optional<String>
) {
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
    HorizontalSplitPane(
        Modifier.padding(start = pad + buttonSizes),
        splitPaneState = SplitPaneState(.8f, true)
    ) {
        first {
            Column {
                TextActions(snackbarHostState)
                optionalPath(
                    { iff -> MiddlePanel(iff) },
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
                    { SidePanel(rootPath = it) },
                    { Text("Open A Dir") }
                )
            }
        }
    }
}