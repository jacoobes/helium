package structs

import androidx.compose.runtime.*
import structs.themes.HeliumTheme
import java.nio.file.Path
import java.util.*

/**
 * Represents State that is transacted between the buttons on left drawer and editor screen
 */
@Stable
class CodeEditorState(
    initialThemeOption: ThemeMode,
    initialTheme: HeliumTheme,
) {
    var themeMode by mutableStateOf(initialThemeOption)
    val directoryChosen = mutableStateOf<Optional<String>>(Optional.empty())
    val currentSelectPath = mutableStateOf<Path?>(null)
    var currentTheme by mutableStateOf(initialTheme)
    var editors = mutableStateOf(mutableStateListOf<Editor>())
}