package structs

import kotlinx.serialization.Serializable

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
    val theme: String = "",
)