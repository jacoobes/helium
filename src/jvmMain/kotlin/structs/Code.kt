package structs

import com.wakaztahir.codeeditor.model.CodeLang
import java.nio.file.Files
import java.nio.file.Path

class Code(private val path: Path, private val suffix: String) {
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    val content by lazy {
        Files.readString(path)
    }

    companion object {
        val Empty = Code(Path.of(""), "default-code")
    }
}