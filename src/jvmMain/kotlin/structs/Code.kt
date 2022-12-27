package structs

import com.wakaztahir.codeeditor.model.CodeLang
import java.io.File


class Code(val content: String, val suffix: String) {
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    companion object {
        fun fromFile(file: File) {
            Code(file.readText(), file.extension)
        }
    }

}