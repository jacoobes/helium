package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.BreadcrumbBarContentProvider
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.auroraBackground
import structs.Settings
import java.io.File


@Composable
fun LeftSidePanel(
    settings: Settings,
) {
    Column (
        modifier = Modifier
            //for now, 15% of the settings' width
            .width((settings.dimensions.width * .15).dp)
            .fillMaxHeight()
            .auroraBackground()
    ) {
        LabelProjection(contentModel = LabelContentModel("Poo")).project()
    }
}