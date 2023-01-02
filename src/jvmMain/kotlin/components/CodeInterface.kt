package components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.FrameWindowScope
import components.drawers.FileNavDrawer
import components.textarea.TextActions
import structs.Code
import structs.Settings

@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
    appBarHeight: Dp,
    snackbarHostState: SnackbarHostState
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
    BoxWithConstraints {
        val buttonSizes = (appBarHeight * 5) / 8
        val pad = (appBarHeight - buttonSizes)
        //temporary padding until i get more values
        Column(Modifier.padding(start = pad+buttonSizes)) {
            BoxWithConstraints {
                TextActions(snackbarHostState)
            }
            BoxWithConstraints {
                MainCodingPanel(currentCode)
            }
        }
        FileNavDrawer()
    }
}