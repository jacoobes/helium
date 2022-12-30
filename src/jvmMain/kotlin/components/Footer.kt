package components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import structs.Code


@Composable
fun RowScope.Footer(code: Code) {
    Text("Something")
    Spacer(Modifier.weight(1f))
    Text(code.lang?.name ?: "Unknown")
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