package structs

import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
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
    private val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    var content: String by mutableStateOf(Files.readString(path))
    fun annotatedString(parser: PrettifyParser, codeTheme: CodeTheme, conten: String) : AnnotatedString {
        this.content = conten
        return parseCodeAsAnnotatedString(parser, codeTheme, lang ?: CodeLang.Default, conten)
    }
    private fun getExtension(): String {
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