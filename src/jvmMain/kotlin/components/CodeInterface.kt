package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.FrameWindowScope
import structs.Code
import structs.Settings
import java.io.File
import javax.swing.filechooser.FileSystemView

@Composable
fun FrameWindowScope.CodeInterface(
    settings: Settings,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        val filesState = remember { mutableStateOf<List<File>>(emptyList()) }
        val fileSystemView = FileSystemView.getFileSystemView()
        BreadcrumbContent(fileSystemView, filesState)
        val currentCode by remember {
            mutableStateOf(
                Code("""
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
        Row(modifier = Modifier.fillMaxHeight(0.95f)) {
            Column(
                modifier = Modifier
                    //for now, 15% of the max width
                    .fillMaxWidth(.15f)
                    .fillMaxHeight().border(1.dp, Color.Red),
            ) {
                LeftPanelCommands()
                LeftSidePanel(fileSystemView, filesState)
            }
            MainCodingPanel(currentCode)
        }
        Row(modifier = Modifier.border(1.dp, Color.Green)) {
            Footer(currentCode)
        }
    }
}