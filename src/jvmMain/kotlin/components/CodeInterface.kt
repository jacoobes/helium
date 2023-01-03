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
import structs.Code
import structs.Settings

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
    snackbarHostState: SnackbarHostState,
    directoryChosen : MutableState<String?>
) {
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
    HorizontalSplitPane(
        Modifier.padding(start = pad+buttonSizes),
        splitPaneState = SplitPaneState(.8f, true)
    ) {

        first {
            Column {
                TextActions(snackbarHostState)
                MainCodingPanel(currentCode)
            }
        }
        second {
            Column(Modifier.fillMaxHeight()) {
                if(directoryChosen.value == null) {
                    Text("Open something up bruh")
                } else {
                    SidePanel(directoryChosen.value!!)
                }
            }
        }
    }
}