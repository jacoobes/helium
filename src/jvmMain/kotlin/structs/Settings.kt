package structs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Theme(val name : String, val mode: ThemeMode?)
@Serializable
enum class ThemeMode {
    @SerialName("Dark") Dark,
    @SerialName("Light") Light,
    None
}
@Serializable
data class Dimensions(
    val width: Int,
    val height: Int
)

@Serializable
data class Settings(
    val editorFont: String = "JetBrainsMono.ttf",
    val codeFont: String = "JetBrainsMono.ttf",
    val dimensions: Dimensions = Dimensions(width = 1920, height = 1080),
    val theme: Theme
)