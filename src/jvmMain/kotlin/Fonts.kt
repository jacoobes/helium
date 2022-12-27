import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
fun createFont(resource: String, weight : FontWeight = FontWeight.W400, style: FontStyle = FontStyle.Normal): FontFamily {
    return FontFamily(
        Font(
            resource,
            weight,
            style,
        )
    )
}
fun jetbrains(weight : FontWeight = FontWeight.W400, style: FontStyle = FontStyle.Normal) : FontFamily {
    return createFont("JetBrainsMono.ttf", weight, style)
}

fun bitstream() : FontFamily {
    return createFont("BitStreamMono.ttf")
}