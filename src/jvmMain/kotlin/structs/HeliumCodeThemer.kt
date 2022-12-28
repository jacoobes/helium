package structs

import androidx.compose.ui.graphics.Color
import com.wakaztahir.codeeditor.theme.CodeTheme
import org.pushingpixels.aurora.theming.AuroraSkinDefinition

class HeliumCodeThemer {
    companion object {
        //Usually for dynamically creating a skin based on the aurora skin present
        fun createThemeFromSkin(
            skin: AuroraSkinDefinition,
            build : (AuroraSkinDefinition) -> CodeTheme): CodeTheme {
            return build(skin)
        }

        inline val Double.f: Float get() = this.toFloat()
        fun Color.complement() : Color {
            return Color(
                red = (1.0 - red).f,
                green = (1.0 - green).f,
                blue = (1.0 - blue).f,
                alpha,
                colorSpace
            )
        }
    }


}


