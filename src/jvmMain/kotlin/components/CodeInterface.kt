package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import structs.Code
import structs.Settings
import java.io.File
import javax.swing.filechooser.FileSystemView

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
    Row(Modifier.fillMaxSize(.95f)) {
        Column(
            modifier = Modifier
                //for now, 15% of the max width
                .fillMaxWidth(.15f)
        ) {
            LeftPanelCommands()
            LeftSidePanel(fileSystemView, filesState)
        }
        MainCodingPanel(currentCode)
    }
}