package structs

import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

interface TreeNode

data class FileNode(val path: Path) : TreeNode
data class DirectoryNode(val path: Path,  val children: List<Path>) : TreeNode