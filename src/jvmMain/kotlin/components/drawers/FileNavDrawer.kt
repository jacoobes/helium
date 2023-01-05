package components.drawers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberComponentRectPositionProvider
import buttonSizes
import components.FileNode
import components.drawers.buttons.DirectoryChooser
import components.drawers.buttons.FileDrawerTitle
import kotlinx.coroutines.launch
import pad
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileNavDrawer(
    snackbarHostState: SnackbarHostState,
    directoryChosen : MutableState<Optional<String>>
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed) { true }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerTonalElevation = 100.dp,
        gesturesEnabled = false,
        drawerShape = RoundedCornerShape(4.dp),
        modifier = Modifier.width(pad+buttonSizes),
        drawerContent = {
            FileDrawerTitle(drawerState)
            DirectoryChooser(enabled = true, directoryChosen)
        }
    ) {
        if (drawerState.isClosed) {
            FileNode {
                scope.launch {
                    drawerState.open()
                }
            }
        }
    }
}