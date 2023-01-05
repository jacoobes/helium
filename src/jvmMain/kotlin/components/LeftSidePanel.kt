package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import structs.DirectoryNode
import structs.FileNode
import structs.TreeNode
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.fileVisitor
import kotlin.io.path.isDirectory
import kotlin.io.path.readAttributes

@Composable
fun getSelectedFile(
    directoryChosen: String? = null,
    path: Path?,
    isSubPath: Boolean,
    selectedDirectoryAndChildren: SnapshotStateList<Path>
) {
    LaunchedEffect(selectedDirectoryAndChildren) {
        withContext(Dispatchers.IO) {
            val curPath = if (directoryChosen == null) {
                path ?: throw Error("Path does not exist")
            } else {
                Path.of(directoryChosen)
            }
            if (!isSubPath) {
                selectedDirectoryAndChildren.add(curPath)
            }
            Files
                .newDirectoryStream(curPath)
                .forEach {
                    selectedDirectoryAndChildren.add(it)
                }
        }
    }
}

@OptIn(ExperimentalPathApi::class)
@Composable
fun SidePanel(
    rootPath: Path
) {
    val li by remember { mutableStateOf(mutableStateListOf<TreeNode>()) }
    val expandedItems = remember { mutableStateListOf<Path>() }
    println("hello")
    val walker = fileVisitor {
        onVisitFile { f, attr ->
            li.add(FileNode(f,attr))
            FileVisitResult.CONTINUE
        }
        onPreVisitDirectory { dir, attr ->
            li.add(DirectoryNode(dir,attr, Files.newDirectoryStream(dir).toList()))
            FileVisitResult.SKIP_SUBTREE
        }
    }
    LaunchedEffect(rootPath) {
        withContext(Dispatchers.IO) {
            Files.walkFileTree(rootPath, walker)
        }
    }

    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(top = 3.dp, bottom = 3.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        tree(
            list= li,
            isExpanded = { expandedItems.contains(it) },
            toggleExpand = {
                if (expandedItems.contains(it)) {
                    expandedItems.remove(it)
                } else {
                    expandedItems.add(it)
                }
            }
        )
    }
}
fun LazyListScope.tree(
    list: List<TreeNode>,
    isExpanded: (Path) -> Boolean,
    toggleExpand: (Path) -> Unit,
) {
    list.forEach {
        if(it is DirectoryNode) {
            directoryNode(it, isExpanded, toggleExpand)
        } else {
            fileNode(it as FileNode)
        }
    }
}
fun LazyListScope.fileNode(
    node: FileNode,
    ) {
    item {
        FileChild(node.path) {
            println(node.path)
        }
    }
}

fun LazyListScope.directoryNode(
    node: DirectoryNode,
    isExpanded: (Path) -> Boolean = {  false  },
    toggleExpanded: (Path) -> Unit = {},
) {

    item {
        var icon by remember { mutableStateOf(Icons.Sharp.KeyboardArrowRight) }
        DirectoryChild(
            node.path,
            leadingIcon = {
                Icon(
                    icon,
                    null,
                    modifier = Modifier.size(20.dp)
                )
            },
            onClick = {
                toggleExpanded(node.path)
                icon = Icons.Sharp.run {
                    if (isExpanded(node.path))
                        KeyboardArrowDown
                    else
                        KeyboardArrowRight
                }
            }
        )
    }
    if (isExpanded(node.path)) {
        val list = node.children.map { path ->
                if(path.isDirectory()) {
                    DirectoryNode(path, path.readAttributes(), Files.newDirectoryStream(path).toList())
                } else {
                    FileNode(path, path.readAttributes())
                }
        }
        tree(list, isExpanded, toggleExpanded)
    }
}