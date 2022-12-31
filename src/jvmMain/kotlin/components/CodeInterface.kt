package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import components.textarea.TextModifiers
import structs.Code
import structs.Settings
import testBorder
import java.io.File
import javax.swing.filechooser.FileSystemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrameWindowScope.CodeInterface(
    settings: Settings,
) {
    val filesState = remember { mutableStateOf<List<File>>(emptyList()) }
    val fileSystemView = FileSystemView.getFileSystemView()
    val currentCode by remember {
        mutableStateOf(
            Code(
                """
                        #[derive(Copy, Clone)]
                        struct o<T>();
                        
                        /// doc comment
                        impl<T> o<T> {
                          // normal comment
                          pub fn a() { unreachable!(); }
                          pub fn as_ref(self) -> &Self { &self }
                        }
                        
                        fn main() -> () {
                          let mut t: &o<i8> = &o();
                          println!(t.as_ref());
                        }
                    """.trimIndent(), "rust"
            )
        )
    }
    Box(
        Modifier.fillMaxSize()
    ) {
        Column {
            TextModifiers()
            MainCodingPanel(currentCode)
        }
        val state by remember { mutableStateOf(DrawerValue.Closed) }
        val drawerState = rememberDrawerState(state) { true }
            DismissibleNavigationDrawer(
                drawerState = drawerState,
                drawerTonalElevation = 100.dp,
                drawerShape = RoundedCornerShape(3.dp),
                modifier = Modifier.border(testBorder),
                drawerContent = {
                    NavigationDrawerItem({ Text("element") }, onClick = {}, selected = true)
                }
            ) {
                Column(Modifier.fillMaxSize()) {
                    FileNode(false)
                }
            }
    }
}