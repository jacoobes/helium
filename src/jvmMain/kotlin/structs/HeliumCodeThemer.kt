package structs

import androidx.compose.material3.ColorScheme
import com.wakaztahir.codeeditor.theme.CodeTheme

class HeliumCodeThemer {
    companion object {
        //Usually for dynamically creating a skin based on the aurora skin present
//        fun createThemeFromSkin(
//            build : () -> CodeTheme): CodeTheme {
//            return build(skin)
//        }

    }
}

class HeliumTheme(
    val light: ColorScheme,
    val dark: ColorScheme,
) {
    fun deriveCodeTheme(dark: Boolean): CodeTheme {

        TODO()
    }
}


