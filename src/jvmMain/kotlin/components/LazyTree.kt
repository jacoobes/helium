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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import structs.DirectoryNode
import structs.FileNode
import structs.TreeNode
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.isDirectory
@Composable
fun LazyFileTree(
    source: List<TreeNode>,
    selectedPath: MutableState<Optional<Path>>
) {
    val expandedItems = remember { mutableStateListOf<Path>() }
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(top = 3.dp, bottom = 3.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        tree(
            list= source,
            selectedPath,
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
    selectedPath: MutableState<Optional<Path>>,
    isExpanded: (Path) -> Boolean,
    toggleExpand: (Path) -> Unit,
) {
    list.forEach {
        when(it) {
            is DirectoryNode -> directoryNode(it, selectedPath, isExpanded, toggleExpand)
            is FileNode -> fileNode(it, selectedPath = selectedPath)
        }
    }
}


fun LazyListScope.fileNode(
    node: FileNode,
    selectedPath: MutableState<Optional<Path>>
) {
    item {
        FileChild(node.path) {
            selectedPath.value = Optional.of(node.path)
        }
    }
}
fun LazyListScope.directoryNode(
    node: DirectoryNode,
    selectedPath: MutableState<Optional<Path>>,
    isExpanded: (Path) -> Boolean = { false },
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
                DirectoryNode(path, Files.newDirectoryStream(path).toList())
            } else {
                FileNode(path)
            }
        }
        tree(list, selectedPath, isExpanded, toggleExpanded)
    }
}