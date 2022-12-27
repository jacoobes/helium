package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import structs.Settings

@Composable
fun AuroraApplicationScope.CommandMenu(
    settings: Settings,
    visibility: MutableState<Boolean>,
    skinDefinition: AuroraSkinDefinition,
    options: List<Pair<String, () -> Unit>>
) {
    //Maybe refactor into Surface one day
    AuroraWindow(
        skin = skinDefinition,
        state = WindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(
                width = (settings.dimensions.width / options.size).dp,
                height = (settings.dimensions.height / options.size).dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.None,
        focusable = true,
        onCloseRequest = { visibility.value = false }
    ) {
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
                  commandPresentationState = CommandButtonPresentationState.TileFitToIcon,
                  iconActiveFilterStrategy = IconFilterStrategy.Original,
                  iconEnabledFilterStrategy = IconFilterStrategy.Original,
                  isMenu = true
              ),
          ).project()
    }
}