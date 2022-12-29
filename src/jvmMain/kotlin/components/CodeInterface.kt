package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Code
import structs.Settings
import java.io.File
import javax.swing.filechooser.FileSystemView

@Composable
fun AuroraWindowScope.CodeInterface(
    settings: Settings,
    skin: MutableState<AuroraSkinDefinition>
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
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                LeftPanelCommands()
                LeftSidePanel(fileSystemView, filesState)
            }
            MainCodingPanel(skin, currentCode)
        }
        Row {
            AuroraDecorationArea(DecorationAreaType.Toolbar) {
                Footer(skin, currentCode)
            }
        }

    }
}