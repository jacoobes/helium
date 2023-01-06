package structs.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.theme.SyntaxColors
//Color(0xFFE9EAF5) possible light theme background white ?
val md_theme_light_primary = Color(0xFF4459A9)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFDCE1FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001552)
val md_theme_light_secondary = Color(0xFF5A5D72)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFDEE1F9)
val md_theme_light_onSecondaryContainer = Color(0xFF171B2C)
val md_theme_light_tertiary = Color(0xFF75546F)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFFD7F4)
val md_theme_light_onTertiaryContainer = Color(0xFF2C1229)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFEFBFF)
val md_theme_light_onBackground = Color(0xFF1B1B1F)
val md_theme_light_surface = Color(0xFFFEFBFF)
val md_theme_light_onSurface = Color(0xFF1B1B1F)
val md_theme_light_surfaceVariant = Color(0xFFE2E1EC)
val md_theme_light_onSurfaceVariant = Color(0xFF45464F)
val md_theme_light_outline = Color(0xFF767680)
val md_theme_light_inverseOnSurface = Color(0xFFF2F0F4)
val md_theme_light_inverseSurface = Color(0xFF303034)
val md_theme_light_inversePrimary = Color(0xFFB7C4FF)

val md_theme_dark_primary = Color(0xFFB7C4FF)
val md_theme_dark_onPrimary = Color(0xFF0C2878)
val md_theme_dark_primaryContainer = Color(0xFF2A4190)
val md_theme_dark_onPrimaryContainer = Color(0xFFDCE1FF)
val md_theme_dark_secondary = Color(0xFFC2C5DD)
val md_theme_dark_onSecondary = Color(0xFF2B3042)
val md_theme_dark_secondaryContainer = Color(0xFF424659)
val md_theme_dark_onSecondaryContainer = Color(0xFFDEE1F9)
val md_theme_dark_tertiary = Color(0xFFE4BADA)
val md_theme_dark_onTertiary = Color(0xFF43273F)
val md_theme_dark_tertiaryContainer = Color(0xFF5C3D56)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFD7F4)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF1B1B1F)
val md_theme_dark_onBackground = Color(0xFFE4E1E6)
val md_theme_dark_surface = Color(0xFF1B1B1F)
val md_theme_dark_onSurface = Color(0xFFE4E1E6)
val md_theme_dark_surfaceVariant = Color(0xFF45464F)
val md_theme_dark_onSurfaceVariant = Color(0xFFC6C5D0)
val md_theme_dark_outline = Color(0xFF90909A)
val md_theme_dark_inverseOnSurface = Color(0xFF1B1B1F)
val md_theme_dark_inverseSurface = Color(0xFFE4E1E6)
val md_theme_dark_inversePrimary = Color(0xFF4459A9)


val seed = Color(0xFF001D69)


private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
)

@Composable
fun DefaultHeliumTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}
class DerivedMonochrome(
    colorScheme: ColorScheme,
) : CodeTheme(
    colorScheme.run {
        val baseColor = onSurface
        SyntaxColors(
            type = tertiary,
            keyword = tertiary,
            literal = onSurface,
            comment = baseColor.copy(alpha = .5f),
            string = onSurface,
            punctuation = onSurface,
            plain = onSurface,
            tag = Color.White,
            declaration = Color.White,
            source = Color.White,
            attrValue = Color.White,
            attrName = Color.White,
            nocode = Color.White
        )
    }
)