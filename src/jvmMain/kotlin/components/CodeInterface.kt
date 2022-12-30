package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import components.textarea.TextModifiers
import structs.Code
import structs.Settings
import testBorder
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
    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxSize(.95f)
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                //for now, 15% of the max width
                .fillMaxWidth(.15f)
        ) {
            LeftPanelCommands()
            LeftSidePanel()
        }

        Column {
            TextModifiers()
            MainCodingPanel(currentCode)
        }
        //LineNumberList(currentCode, emptyArray())
    }
}