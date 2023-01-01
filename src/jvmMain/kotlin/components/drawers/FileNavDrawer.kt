package components.drawers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.FileNode
import kotlinx.coroutines.launch
import testBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileNavDrawer() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed) { true }
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerTonalElevation = 100.dp,
            drawerContent = {
                NavigationDrawerItem({ Text("element") }, onClick = {}, selected = false)
            }
        ) {
            FileNode {
                scope.launch {
                    if(drawerState.isClosed) drawerState.open() else drawerState.close()
                }
            }
        }
    }

}