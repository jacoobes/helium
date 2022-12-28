package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.helium.scalable.svg.code_plus
import com.helium.scalable.svg.folders
import com.helium.scalable.svg.folders_off
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.window.AuroraDecorationArea
@Composable
fun LeftPanelCommands() {
    AuroraDecorationArea(DecorationAreaType.ControlPane) {
        val markAsWorkingDir = Command(
            text = "",
            icon = code_plus(),
            action = {},
            actionRichTooltip = RichTooltip("Edit at this directory")
        )
        val closeAll = Command(
            text = "",
            icon = folders_off(),
            action = {},
            actionRichTooltip = RichTooltip("Close all child directories")
        )
        val openAll = Command(
            text = "",
            icon = folders(),
            action = {},
            actionRichTooltip = RichTooltip("Open all child directories")
        )
        CommandButtonStripProjection(
            contentModel = CommandGroup(
                commands = listOf(
                    markAsWorkingDir,
                    closeAll,
                    openAll
                )
            ),
            presentationModel = CommandStripPresentationModel(
                orientation = StripOrientation.Horizontal,
                commandPresentationState = CommandButtonPresentationState.MediumFitToIcon,
                horizontalAlignment = HorizontalAlignment.Center,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                horizontalGapScaleFactor = 3f,
            )
        ).project(
            Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = AuroraSkin.colors.getBackgroundColorScheme(DecorationAreaType.ControlPane).lineColor
        ))
    }

}