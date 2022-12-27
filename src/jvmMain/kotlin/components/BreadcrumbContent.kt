package components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraBreadcrumbBar
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.*
import java.io.File
import javax.swing.filechooser.FileSystemView

@Composable
fun AuroraWindowScope.BreadcrumbContent() {
    val scope = rememberCoroutineScope()

    val fileSystemView = FileSystemView.getFileSystemView()
    val breadcrumbBarContentProvider =
        object : BreadcrumbBarContentProvider<File>() {
            override fun getDisplayText(item: File?): String {
                if (item == null) {
                    return ""
                }
                return fileSystemView.getSystemDisplayName(item)
                    .let { name -> name.ifEmpty { item.absolutePath } }
            }

            override fun getIcon(item: File?): Painter? {
                return if (item?.isDirectory == true) null else null
            }

            override suspend fun getPathChoices(item: File?): List<File> {
                // If our item is null, get the file system roots. Otherwise, get all files under
                // this file item.
                val candidates =
                    (if (item == null) fileSystemView.roots else item.listFiles())
                        ?: return emptyList()

                // Now filter out hidden ones and non-directories, map the rest to
                // what the content provider needs to return, and sort them by display name
                return candidates.filterNot { !it.isDirectory || fileSystemView.isHiddenFile(it) }
                    .map { it }
                    .sortedBy { getDisplayText(it).lowercase() }
            }

            override suspend fun getLeaves(item: File): List<File> {
                // Get all files under the file item, filter out hidden ones and
                // directory ones, map the rest to what the content provider needs to
                // return, and sort them by display name
                val candidates = item.listFiles() ?: return emptyList()
                return candidates
                    .filterNot { it.isDirectory || fileSystemView.isHiddenFile(it) }
                    .map { it }
                    .sortedBy { getDisplayText(it).lowercase() }
            }
        }

    val commandPanelContentModel = remember { mutableStateOf<CommandPanelContentModel?>(null) }
    val breadcrumbBarHorizontalScrollState = rememberScrollState(0)
    val onBreadcrumbItemSelected: (File) -> Unit = {
        scope.launch(Dispatchers.Default) {
            commandPanelContentModel.value =
                getCommandPanelContent(breadcrumbBarContentProvider, it)
            delay(150)
            breadcrumbBarHorizontalScrollState.animateScrollTo(
                breadcrumbBarHorizontalScrollState.maxValue
            )
        }
    }

    val breadcrumbBarContentModel = BreadcrumbBarContentModel(
        contentProvider = breadcrumbBarContentProvider,
        onItemSelected = onBreadcrumbItemSelected
    )

    Column {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            AuroraBreadcrumbBar(
                contentModel = breadcrumbBarContentModel,
                presentationModel = BreadcrumbBarPresentationModel(
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                ),
                horizontalScrollState = breadcrumbBarHorizontalScrollState,
                modifier = Modifier.fillMaxWidth().auroraBackground()
                    .padding(horizontal = 2.dp, vertical = 4.dp)
            )
        }

        if (commandPanelContentModel.value == null) {

        } else {
            CommandButtonPanelProjection(
                contentModel = commandPanelContentModel.value!!,
                presentationModel = CommandPanelPresentationModel(
                    layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Adaptive(140.dp)),
                    showGroupLabels = false,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    commandPresentationState = CommandButtonPresentationState.Medium,
                    commandHorizontalAlignment = HorizontalAlignment.Leading,
                    commandTextOverflow = TextOverflow.Ellipsis,
                    iconActiveFilterStrategy = IconFilterStrategy.Original,
                    iconEnabledFilterStrategy = IconFilterStrategy.Original
                )
            ).project()
        }
    }
}

private suspend fun getCommandPanelContent(
    contentProvider: BreadcrumbBarContentProvider<File>,
    selected: File
): CommandPanelContentModel {
    val leaves = contentProvider.getLeaves(selected)
    return CommandPanelContentModel(
        commandGroups = listOf(
            CommandGroup(
                title = null,
                leaves.map { leaf ->
                    val extension = leaf.extension.lowercase()

                    val className =
                        "org.pushingpixels.aurora.demo.svg.filetypes.ext_${extension}"
                    var icon: Painter? = null
                    try {
                        val transcodedClass = Class.forName(className)
                        val ctr = transcodedClass.getConstructor()
                        icon = ctr.newInstance() as Painter
                    } catch (_: Throwable) {
                    }

                    Command(
                        text = contentProvider.getDisplayText(leaf),
                        icon = icon,
                        action = {})
                }
            )
        )
    )
}