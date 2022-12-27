package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraDecorationArea

@Composable
fun BoxScope.CommandMenu(
    visibility: MutableState<Boolean>,
    options: List<Pair<String, () -> Unit>>
) {
    //Maybe refactor into Surface one day
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        if(visibility.value) {
            AuroraDecorationArea(DecorationAreaType.Footer) {
                CommandButtonPanelProjection(
                    contentModel = CommandPanelContentModel(
                        commandGroups = listOf(
                            CommandGroup(
                                commands = options.map { (title, action) ->
                                    Command(
                                        title,
                                        action = action
                                    )
                                }))
                    ),
                    presentationModel = CommandPanelPresentationModel(
                        layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Fixed(1)),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        commandTextStyle = TextStyle(
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Bold
                        ),
                        popupPlacementStrategy = PopupPlacementStrategy.CenteredVertically.HAlignStart,
                        commandPresentationState = CommandButtonPresentationState.TileFitToIcon,
                        iconActiveFilterStrategy = IconFilterStrategy.Original,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original,
                        isMenu = true
                    ),
                ).project(Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }

}