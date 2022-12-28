package structs

import androidx.compose.ui.graphics.Color
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.theme.SyntaxColors
//fun Int.hexToString() = String.format("#%06X", 0xFFFFFF and this)
object HeliumThemes {

    /**
     * First theme!!!!!
     *
    **/
    class NightShadeMonoChrome : CodeTheme(
        SyntaxColors(
            type = Color.White,
            keyword = Color( 188, 188, 242), //Lavender
            literal = Color(223,223,238), // Light Lavender
            comment = Color(223,223,238, 255/2), // Light Lavender, Alpha / 2
            string = Color( 188, 188, 242),
            punctuation = Color(223,223,238),
            plain = Color.White,
            tag = Color.White,
            declaration = Color.White,
            source = Color.White,
            attrValue = Color.White,
            attrName = Color.White,
            nocode = Color.White
        )
    )
}
