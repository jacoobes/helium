package components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import buttonSizes
import components.textarea.TextActions
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import pad
import structs.Settings
import java.io.BufferedReader
import java.nio.file.Path

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
    snackbarHostState: SnackbarHostState,
    directoryChosen : MutableState<String?>
) {
    val currentPath = remember { mutableStateOf<Path?>(null) }
    HorizontalSplitPane(
        Modifier.padding(start = pad+buttonSizes),
        splitPaneState = SplitPaneState(.8f, true)
    ) {
        first {
            Column {
                TextActions(snackbarHostState)
                MainCodingPanel(currentPath)
            }
        }
        second {
            Column(Modifier.fillMaxHeight()) {
                if(directoryChosen.value == null) {
                    Text("Open something up bruh")
                } else {
                    SidePanel(
                        directoryChosen = directoryChosen.value!!,
                        currentFile = currentPath
                    )
                }
            }
        }
    }
}