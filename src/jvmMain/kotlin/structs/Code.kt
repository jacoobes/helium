package structs

import androidx.compose.runtime.Stable
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.writeText

@Stable
data class Code(val path: Path) {
    private val suffix = getExtension()
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    val content: String by lazy {
        Files.readString(path)
    }
    private fun getExtension() : String {
        var extension = ""

        val i: Int = path.name.lastIndexOf('.')
        if (i > 0) {
            extension = path.name.substring(i + 1)
        }
        return extension
    }
    suspend fun save(charset: Charset = Charsets.UTF_8) {
        withContext(Dispatchers.IO) {
            path.writeText(content, charset)
        }
    }
}