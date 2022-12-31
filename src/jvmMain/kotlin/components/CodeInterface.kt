package components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import components.drawers.FileNavDrawer
import components.textarea.TextModifiers
import structs.Code
import structs.Settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrameWindowScope.MainCodeLayout(
    settings: Settings,
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
        //temporary padding until i get more values
        Column(Modifier.padding(start = 24.dp)) {
            TextModifiers()
            MainCodingPanel(currentCode)
        }
        FileNavDrawer()
    }
}