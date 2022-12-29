package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.helium.scalable.svg.folder
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import java.io.File
import javax.swing.filechooser.FileSystemView


@Composable
fun ColumnScope.LeftSidePanel(
    fileView : FileSystemView,
    filesState: MutableState<List<File>>
) {
    AuroraDecorationArea(DecorationAreaType.Toolbar) {
        if(filesState.value.isEmpty()) {
            LabelProjection(
                contentModel = LabelContentModel(
                    "Open a directory with the breadcrumb bar"
                    )
            ).project(
                Modifier.fillMaxHeight()
            )
        } else {
            val lazyListState = rememberLazyListState()
            val stateSelection = remember { mutableStateOf<File?>(null) }
            LazyColumn(
                modifier= Modifier.fillMaxSize()
                    .padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness),
                state = lazyListState
            ) {
                this.items(filesState.value) { item ->
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth(),
                        selected = (stateSelection.value == item),
                        onClick = { stateSelection.value = item },
                        sides = Sides(straightSides = Side.values().toSet())
                    ) {
                        val displayName = fileView.getSystemDisplayName(item)
                            .let { name -> name.ifEmpty { item.absolutePath } }

                            LabelProjection(
                                contentModel = LabelContentModel(displayName, icon = folder()),
                                presentationModel = LabelPresentationModel(
                                    textOverflow = TextOverflow.Visible,
                                    horizontalAlignment = HorizontalAlignment.Leading,
                                    textMaxLines = 1,
                                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
                                ),
                            ).project()
                    }
                }
            }
            if(stateSelection.value != null) {
            }
        }
    }
}