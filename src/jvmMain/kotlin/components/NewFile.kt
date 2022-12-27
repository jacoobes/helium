package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.window.AuroraWindowScope
import structs.Settings

@Composable
fun AuroraWindowScope.NewFile(
    settings: MutableState<Settings>,
    show: MutableState<Boolean>,
    skin: MutableState<AuroraSkinDefinition>
) {

}