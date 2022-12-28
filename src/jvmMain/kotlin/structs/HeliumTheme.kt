package structs

import androidx.compose.ui.graphics.Color
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.theme.MonokaiTheme
import com.wakaztahir.codeeditor.theme.SyntaxColors
import org.jetbrains.skia.Matrix33
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import kotlin.math.*

class HeliumTheme(
    val skinDefinition: AuroraSkinDefinition,
    val theme: CodeTheme?
) {
    fun createThemeBasedOffSkin(
        type : Color
    ): CodeTheme {
        val auroraColors = skinDefinition.colors
        val auroraControlPanels = auroraColors.getBackgroundColorScheme(DecorationAreaType.ControlPane)
        val auroraFooters = auroraColors.getBackgroundColorScheme(DecorationAreaType.Header)
        val codeBackground = auroraControlPanels.backgroundFillColor
        val skinCompliment = auroraFooters.backgroundFillColor.complement()
        //MonokaiTheme()
        val generatedTheme = object: CodeTheme(
            colors = SyntaxColors(
                type = type.complement(),
                keyword = skinCompliment,
                string = skinCompliment, //monokai
                punctuation =  Color(0xFFC1C1C1),
                plain = skinCompliment,
                tag = Color(0xFFF92672),
                declaration = Color(0xFFF92672),
                source = Color(0xFFF92672),
                attrName = Color(0xFFF92672),
                attrValue = Color(0xFFF92672),
                literal = skinCompliment,
                nocode = skinCompliment,
                comment = Color(0xFF76715E)//monokai
            )
        ) {}
        return generatedTheme
    }
    private inline val Double.f: Float get() = this.toFloat()
    private fun Color.complement() : Color {
        return Color(
            red = (1.0 - red).f,
            green = (1.0 - green).f,
            blue = (1.0 - blue).f,
            alpha,
            colorSpace
        )
    }
    private fun Color.hueShift(degrees: Double): Color {
        val (roy, gee, biv, alpha, cs) = this
        val acos = cos(Math.toRadians(degrees)).f
        val asin = sin(Math.toRadians(degrees)).f

        val `⅓` = 1/3f
        val `sq⅓` = sqrt(`⅓`)
        val matrix = Matrix33(
            acos+(1f-acos)/3f,              `⅓`*(1f-acos)-`sq⅓`*asin,       `⅓`*(1f-acos)+`sq⅓`*asin,
            `⅓`*(1f-acos)+`sq⅓`*asin,       acos+`⅓`*(1f-acos),             `⅓`*(1f-acos)-`sq⅓`*asin,
            `⅓`*(1f-acos)-`sq⅓`*asin,       `⅓`*(1f-acos)+`sq⅓`*asin,       acos+`⅓`*(1f-acos),
        )
        return Color(
            red = (roy * matrix[0,0] + gee * matrix[0,1] + biv * matrix[0,2]).coerceIn(0f,255f),
            green = (roy * matrix[1,0] + gee * matrix[1,1] + biv * matrix[1,2]).coerceIn(0f,255f),
            blue = (roy * matrix[2,0] + gee * matrix[2,1] + biv * matrix[2,2]).coerceIn(0f,255f),
            alpha = alpha,
            colorSpace = cs
        )
    }
}

private operator fun Matrix33.get(i: Int, i1: Int): Float {
    return mat[i*3+i1]
}
