package structs

import kotlinx.coroutines.*
import utils.aRead
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.file.Path

fun CoroutineScope.loadSettings(buf : ByteBuffer): Job {
    return launch {
        val aChannel = withContext(Dispatchers.IO) {
            AsynchronousFileChannel.open(
                Path.of("src", "jvmMain", "resources", "settings.json")
            )
        }
        aChannel.use { channel ->
            channel.aRead(buf)
        }
    }
}
