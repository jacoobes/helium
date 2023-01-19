package structs.themes

import androidx.compose.material3.ColorScheme
import com.wakaztahir.codeeditor.theme.CodeTheme
import structs.ThemeMode

interface HeliumTheme {
    val name: String
    fun createTheme(option : ThemeMode) : ColorScheme

    fun syntaxHighLighting(option: ThemeMode) : CodeTheme
}

val heliumThemeResolver by lazy {
    hashMapOf<String, HeliumTheme>(
        "Default" to DefaultHeliumTheme()
    )
}