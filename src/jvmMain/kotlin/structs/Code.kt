package structs

import com.wakaztahir.codeeditor.model.CodeLang
import java.nio.file.Path
import kotlin.io.path.bufferedReader
import kotlin.streams.asSequence

class Line(val number: Int, val content: CharArray) {
    operator fun get(el: Int): Char {
        return content[el]
    }
}

class Code(private val content: Path, private val suffix: String) {
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    val matrix by lazy {
        content
            .bufferedReader()
            .lines()
            .asSequence()
            .mapIndexed { index, s ->
                Line(index + 1, s.toCharArray())
            }
    }

    companion object {
        val Empty = Code(Path.of(""), "default-code")
    }
}