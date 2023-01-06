package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import structs.DirectoryNode
import structs.FileNode
import structs.TreeNode
import utils.dirStream
import java.nio.file.Path
import java.util.*
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.isDirectory


@Composable
fun SidePanel(
    rootPath: Path,
    selectedPath: MutableState<Optional<Path>>
) {
    var rootDir by remember(rootPath) {
        mutableStateOf<List<TreeNode>>(emptyList())
    }
    if (rootDir.isEmpty()) {
        LoadingPanel()
        val scope = rememberCoroutineScope()
        scope.launch(Dispatchers.IO) {
            rootDir = dirStream(rootPath).use {
                it.map { path ->
                    if (path.isDirectory()) {
                        DirectoryNode(path, dirStream(path).toList())
                    } else {
                        FileNode(path)
                    }
                }
            }
        }
    } else {
        LazyFileTree(rootDir, selectedPath)
    }
}

@Composable
fun LoadingPanel() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

