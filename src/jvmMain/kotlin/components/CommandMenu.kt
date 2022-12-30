package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment

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
        if (visibility.value) {
//                CommandButtonPanelProjection(
//                    contentModel = CommandPanelContentModel(
//                        commandGroups = listOf(
//                            CommandGroup(
//                                commands = options.map { (title, action) ->
//                                    Command(
//                                        title,
//                                        action = action
//                                    )
//                                }))
//                    ),
//                    presentationModel = CommandPanelPresentationModel(
//                        layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Fixed(1)),
//                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
//                        commandTextStyle = TextStyle(
//                            textAlign = TextAlign.Left,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        popupPlacementStrategy = PopupPlacementStrategy.CenteredVertically.HAlignStart,
//                        commandPresentationState = CommandButtonPresentationState.TileFitToIcon,
//                        iconActiveFilterStrategy = IconFilterStrategy.Original,
//                        iconEnabledFilterStrategy = IconFilterStrategy.Original,
//                        isMenu = true
//                    ),
//                ).project(Modifier.align(Alignment.CenterHorizontally))
        }
    }

}