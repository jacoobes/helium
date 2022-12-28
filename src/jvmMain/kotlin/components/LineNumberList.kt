package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground

@Composable
fun LineNumberList(
    lineTops : Array<Float>,
    style : TextStyle = TextStyle(fontFamily = jetbrains()),
) {
    val density = LocalDensity.current
    Column(
        Modifier
            .auroraBackground()
            .fillMaxHeight()
            .border(1.dp,  AuroraSkin.colors.getBackgroundColorScheme(DecorationAreaType.ControlPane).lineColor)
    ) {
        if (lineTops.isNotEmpty()) {
            Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                lineTops.forEachIndexed { index, top ->
                    LabelProjection(
                        contentModel = LabelContentModel(
                            (index+1).toString()
                        ),
                        presentationModel = LabelPresentationModel(
                            textStyle = style
                        )
                    ).project(
                        Modifier.offset(y = with(density) { top.toDp() })
                    )
                }
            }
        }
    }
}