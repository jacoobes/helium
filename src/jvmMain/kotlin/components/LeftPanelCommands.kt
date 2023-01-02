package components
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.semantics.Role
//import androidx.compose.ui.unit.dp
//import com.helium.scalable.svg.folders
//import com.helium.scalable.svg.folders_off
//
//@Composable
//fun LeftPanelCommands() {
//    Row(
//        Modifier
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        val (iconSize, setIconSize) = remember { mutableStateOf(20.dp) }
//
//        Box(
//            Modifier
//                .clickable(onClick = { println("hello") }, role = Role.Button)
//                .padding(PaddingValues(2.dp))
//        ) {
//            Icon(
//                folders_off(),
//                null,
//                Modifier.size(iconSize).fillMaxSize(),
//            )
//        }
//        Box(
//            Modifier
//                .clickable(onClick = { println("hello") }, role = Role.Button)
//                .padding(PaddingValues(2.dp))
//        ) {
//            Icon(folders(), null, Modifier.size(iconSize).fillMaxSize())
//        }
//    }
//    DividerLessAlpha(alpha = .50f)
//
//}