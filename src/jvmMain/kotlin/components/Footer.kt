package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import structs.Code


@Composable
fun FrameWindowScope.Footer(code: Code) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp, vertical = 2.dp),
    ) {
        Text("Something")
        Spacer(Modifier.weight(1f))
        Text(code.lang?.name ?: "Unknown")
    }
}

@Composable
fun AuroraSkinSwitcher(
) {
//    val currentSkinDisplayName = AuroraSkin.displayName
//    val auroraSkins = getAuroraSkins()
//    val selectedSkinItem =
//        remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

//    ComboBoxProjection(
//        contentModel = ComboBoxContentModel(
//            items = auroraSkins,
//            selectedItem = selectedSkinItem.value,
//            onTriggerItemSelectedChange = {
//                selectedSkinItem.value = it
//                onSkinChange.invoke(it.second.invoke())
//            }
//        ),
//        presentationModel = ComboBoxPresentationModel(
//            displayConverter = { it.first },
//            popupPlacementStrategy = popupPlacementStrategy,
//            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
//        )
//    ).project()
}