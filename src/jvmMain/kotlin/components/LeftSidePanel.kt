package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea


@Composable
fun LeftSidePanel(commandPanelContentModel: MutableState<CommandPanelContentModel?>) {
    AuroraDecorationArea(DecorationAreaType.Footer) {
        Column(
            modifier = Modifier
                //for now, 15% of the max width
                .fillMaxWidth(.15f)
                .auroraBackground()
                .fillMaxHeight()
        ) {
            if(commandPanelContentModel.value == null) {
                LabelProjection(
                    contentModel = LabelContentModel(
                    "Open a directory with the breadcrumb bar"
                    )
                ).project()
            } else {
                CommandButtonPanelProjection(
                        contentModel = commandPanelContentModel.value!!,
                        presentationModel = CommandPanelPresentationModel(
                            layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Fixed(1)),
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
}