package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.io.File
import javax.swing.filechooser.FileSystemView


@Composable
fun ColumnScope.LeftSidePanel(
    fileView: FileSystemView,
    filesState: MutableState<List<File>>
) {
    val lazyListState = rememberLazyListState()
    val stateSelection = remember { mutableStateOf<File?>(null) }
    val fillerData = remember { mutableStateOf(emptyList<Int>()) }
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        state = lazyListState
    ) {
        items(fillerData.value) { item ->

//                    AuroraBoxWithHighlights(
//                        modifier = Modifier.fillMaxWidth(),
//                        selected = (stateSelection.value == item),
//                        onClick = { stateSelection.value = item },
//                        sides = Sides(straightSides = Side.values().toSet())
//                    ) {
//                        val displayName = fileView.getSystemDisplayName(item)
//                            .let { name -> name.ifEmpty { item.absolutePath } }
//
//                            LabelProjection(
//                                contentModel = LabelContentModel(displayName, icon = folder()),
//                                presentationModel = LabelPresentationModel(
//                                    textOverflow = TextOverflow.Visible,
//                                    horizontalAlignment = HorizontalAlignment.Leading,
//                                    textMaxLines = 1,
//                                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
//                                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
//                                ),
//                            ).project()
//                    }
        }
        if (stateSelection.value != null) {
        }
    }
}