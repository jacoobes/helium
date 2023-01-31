package components.lazytree

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import components.LazySidePanelColumn
import structs.DirectoryNode
import structs.FileNode
import structs.TreeNode
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.name

@Composable
fun LazyFileTree(
    rootPath: Path,
    source: List<TreeNode>,
    selectedPath: MutableState<Path?>
) {

    val expandedItems = remember { mutableStateListOf<Path>() }
    val initDepth = 1
    val listState = rememberLazyListState()

    LazySidePanelColumn(
        contentPadding = PaddingValues(top = 3.dp, bottom = 3.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Text(
                text=rootPath.name+" /",
                modifier = Modifier.padding(start = 9.dp),
                style =  MaterialTheme.typography.titleLarge
            )
        }
        tree(
            list = source,
            depth = initDepth,
            selectedPath =selectedPath,
            isExpanded = { expandedItems.contains(it) }
        ) {
            if (expandedItems.contains(it)) {
                expandedItems.remove(it)
            } else {
                expandedItems.add(it)
            }
        }
    }
}


fun LazyListScope.tree(
    list: List<TreeNode>,
    depth: Int,
    selectedPath: MutableState<Path?>,
    isExpanded: (Path) -> Boolean,
    toggleExpand: (Path) -> Unit,
) {
    list.forEach {
        when(it) {
            is DirectoryNode -> directoryNode(
                node =it,
                depth =depth,
                selectedPath =selectedPath,
                isExpanded =isExpanded,
                toggleExpand =toggleExpand
            )
            is FileNode -> fileNode(
                node=it,
                selectedPath = selectedPath,
                depth = depth
            )
        }
    }
}


fun LazyListScope.fileNode(
    node: FileNode,
    depth: Int,
    selectedPath: MutableState<Path?>
) {
    item {
        FileChild(
            node.path,
            depth = depth) {
            selectedPath.value = node.path
        }
    }
}
fun LazyListScope. directoryNode(
    node: DirectoryNode,
    depth: Int,
    selectedPath: MutableState<Path?>,
    isExpanded: (Path) -> Boolean = { false },
    toggleExpand: (Path) -> Unit = {},
) {
    item {
        var icon by remember { mutableStateOf(Icons.Sharp.KeyboardArrowRight) }
        DirectoryChild(
            node.path,
            depth = depth,
            leadingIcon = {
                Icon(
                    icon,
                    null,
                    modifier = Modifier.size(20.dp)
                )
            },
            onClick = {
                toggleExpand(node.path)
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
                DirectoryNode(path, Files.newDirectoryStream(path).toList())
            } else {
                FileNode(path)
            }
        }
        tree(list, depth+8, selectedPath, isExpanded, toggleExpand)
    }
}