package structs

import androidx.compose.runtime.rememberCoroutineScope
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import kotlin.coroutines.suspendCoroutine
import kotlin.io.path.bufferedReader
import kotlin.io.path.name


class Code(val content: Path, private val suffix: String) {
    val lang = CodeLang.values().find { it.value.toHashSet().contains(suffix) }
    companion object {
        val Empty = Code(Path.of(""), "default-code")
    }
    suspend fun toCharMatrix() {
        val reader = content.bufferedReader()
        coroutineScope {
            withContext(Dispatchers.Default) {

            }
        }
    }
}