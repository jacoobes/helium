package structs

import androidx.compose.runtime.*
import structs.themes.HeliumTheme
import java.util.*

/**
 * Represents State that is transacted between the buttons on left drawer and editor screen
 */
@Stable
class DrawerButtonsState(
    initialThemeOption: ThemeMode,
    initialTheme: HeliumTheme,
) {
    var themeMode by mutableStateOf(initialThemeOption)
    val directoryChosen = mutableStateOf<Optional<String>>(Optional.empty())
    var currentTheme by mutableStateOf(initialTheme)
}