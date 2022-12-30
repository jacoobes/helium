package structs

import kotlinx.coroutines.*
import utils.aRead
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.file.Path

fun CoroutineScope.loadSettingsAsync(): Deferred<ByteBuffer> {
    return async {
        val aChannel = withContext(Dispatchers.IO) {
            AsynchronousFileChannel.open(
                Path.of("src", "jvmMain", "resources", "settings.json")
            )
        }
        aChannel.use { channel ->
            val buf = ByteBuffer.allocate(4096)
            channel.aRead(buf)
            buf
        }
    }
}
