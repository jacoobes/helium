package components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import java.io.File
import javax.swing.filechooser.FileSystemView

@Composable
fun FrameWindowScope.BreadcrumbContent(
    fileSystemView : FileSystemView,
    cPanelState: MutableState<List<File>>
) {
    val scope = rememberCoroutineScope()
    Column( modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .border(1.dp, Color.Yellow)
    ) {
        Text("Breadcrumbs")
    }
//    val breadcrumbBarContentProvider =
//        object : BreadcrumbBarContentProvider<File>() {
//            override fun getDisplayText(item: File?): String {
//                if (item == null) {
//                    return ""
//                }
//                return fileSystemView.getSystemDisplayName(item)
//                    .let { name -> name.ifEmpty { item.absolutePath } }
//            }
//
//            override fun getIcon(item: File?): Painter? {
//                return if (item?.isDirectory == true) null else null
//            }
//
//            override suspend fun getPathChoices(item: File?): List<File> {
//                // If our item is null, get the file system roots. Otherwise, get all files under
//                // this file item.
//                val candidates =
//                    (if (item == null) fileSystemView.roots else item.listFiles())
//                        ?: return emptyList()
//
//                // Now filter out hidden ones and non-directories, map the rest to
//                // what the content provider needs to return, and sort them by display name
//                return candidates.filter { it.isDirectory || !it.isFile }
//                    .map { it }
//                    .sortedBy { getDisplayText(it).lowercase() }
//            }
//
//            override suspend fun getLeaves(item: File): List<File> {
//                // Get all files under the file item, filter out hidden ones and
//                // directory ones, map the rest to what the content provider needs to
//                // return, and sort them by display name
//                val candidates = item.listFiles() ?: return emptyList()
//                return candidates
//                    .filterNot { fileSystemView.isHiddenFile(it) }
//                    .map { it }
//                    .sortedBy { getDisplayText(it).lowercase() }
//            }
//        }
    val breadcrumbBarHorizontalScrollState = rememberScrollState(0)
//    val onBreadcrumbItemSelected: (File) -> Unit = {
//        scope.launch(Dispatchers.Default) {
//            cPanelState.value =
//                getCommandPanelContent(breadcrumbBarContentProvider, it)
//            delay(150)
//            breadcrumbBarHorizontalScrollState.animateScrollTo(
//                breadcrumbBarHorizontalScrollState.maxValue
//            )
//        }
//    }
//    val breadcrumbBarContentModel = BreadcrumbBarContentModel(
//        contentProvider = breadcrumbBarContentProvider,
//        onItemSelected = onBreadcrumbItemSelected
//    )

 //   Column {
//            AuroraBreadcrumbBar(
//                contentModel = breadcrumbBarContentModel,
//                presentationModel = BreadcrumbBarPresentationModel(
//                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
//                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
//                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
//                ),
//                horizontalScrollState = breadcrumbBarHorizontalScrollState,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .auroraBackground()
//                    .padding(horizontal = 2.dp, vertical = 4.dp)
//            )
//    }
}

//suspend fun getCommandPanelContent(
//    contentProvider: BreadcrumbBarContentProvider<File>,
//    selected: File
//): List<File> {
//    return contentProvider.getLeaves(selected)
//}