package components.drawers

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import components.FileNode
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileNavDrawer() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed) { true }
    DismissibleNavigationDrawer(
        drawerState = drawerState,
        drawerTonalElevation = 100.dp,
        drawerShape = RoundedCornerShape(3.dp),
        drawerContent = {
            NavigationDrawerItem({ Text("element") }, onClick = {}, selected = true)
        }
    ) {
        FileNode(true) {
            scope.launch {
                if(drawerState.isClosed) drawerState.open() else drawerState.close()
            }
        }
    }
}