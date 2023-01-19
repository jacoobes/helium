package structs

import androidx.compose.runtime.*
import java.nio.file.Path
import java.util.*

/**
 * Represents State that is transacted between the buttons on left drawer and editor screen
 */
@Stable
class DrawerButtonsState(
    initialThemeOption : ThemeMode
) {
    var themeMode by mutableStateOf(initialThemeOption)
    var pathSelected = mutableStateOf<Path?>(null)
    val directoryChosen =  mutableStateOf<Optional<String>>(Optional.empty())
}