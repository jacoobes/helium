package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Code


@Composable
fun AuroraWindowScope.Footer(skin: MutableState<AuroraSkinDefinition>, code: Code) {
    Row(
        modifier = Modifier
            .auroraBackground()
            .fillMaxHeight()
            .padding(horizontal = 4.dp, vertical = 2.dp),
    ) {
        AuroraSkinSwitcher(
            onSkinChange = { skin.value = it },
            popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart
        )
        Spacer(Modifier.weight(1f))
        LabelProjection(
            contentModel = LabelContentModel(code.lang?.name ?: "Unknown")
        ).project()
    }
}

@Composable
fun AuroraSkinSwitcher(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
) {
    val currentSkinDisplayName = AuroraSkin.displayName
    val auroraSkins = getAuroraSkins()
    val selectedSkinItem =
        remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

    ComboBoxProjection(
        contentModel = ComboBoxContentModel(
            items = auroraSkins,
            selectedItem = selectedSkinItem.value,
            onTriggerItemSelectedChange = {
                selectedSkinItem.value = it
                onSkinChange.invoke(it.second.invoke())
            }
        ),
        presentationModel = ComboBoxPresentationModel(
            displayConverter = { it.first },
            popupPlacementStrategy = popupPlacementStrategy,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
        )
    ).project()
}