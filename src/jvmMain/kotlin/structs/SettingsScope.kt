package structs

import kotlinx.coroutines.*
import utils.aRead
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.file.Path
import kotlin.coroutines.CoroutineContext

object SettingsScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()
    fun loadSettingsAsync(): Deferred<ByteBuffer> {
        return async {
            val aChannel = AsynchronousFileChannel.open(
                Path.of("src", "jvmMain", "resources",  "settings.json")
            )
            aChannel.use { channel ->
                val buf = ByteBuffer.allocate(4096)
                channel.aRead(buf)
                buf
            }
        }
    }
}