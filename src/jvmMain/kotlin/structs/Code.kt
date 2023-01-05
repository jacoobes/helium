package structs

import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name


class Code(val content: String, val suffix: String) {
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    companion object {
        val Empty = Code("", "default-code")
    }

}