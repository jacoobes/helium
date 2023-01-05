package structs

import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

interface TreeNode

data class FileNode(val path: Path, val attr: BasicFileAttributes) : TreeNode
data class DirectoryNode(val path: Path, val attr: BasicFileAttributes, val children: List<Path>) : TreeNode