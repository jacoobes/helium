package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.io.File

@Composable
fun FileDialog(
    window: ComposeWindow,
    title: String,
    allowedExtensions: List<String>,
    allowMultiSelection: Boolean = true,
    onCloseRequest: (result: Set<File>) -> Unit
) {
    AwtWindow(
        create = {
            object : FileDialog(window, title, LOAD) {
                init {
                    isMultipleMode = allowMultiSelection
                    // windows
                    file = allowedExtensions.joinToString(";") { "*$it" } // e.g. '*.jpg'
                    // linux
                    setFilenameFilter { _, name ->
                        allowedExtensions.any {
                            name.endsWith(it)
                        }
                    }
                }

                override fun setVisible(value: Boolean) {
                    super.setVisible(value)
                    if (value) {
                        onCloseRequest(files.toSet())
                    }
                }
            }
        },
        dispose = FileDialog::dispose
    )

}