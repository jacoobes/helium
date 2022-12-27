package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.window.AuroraDecorationArea
import structs.Settings

@Composable
fun TextArea(
    settings: MutableState<Settings>,
    initialText: TextFieldValue?,
    style : TextStyle = TextStyle(fontFamily = jetbrains())
) {
    AuroraDecorationArea(
        DecorationAreaType.ControlPane
    ) {
        var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
        val density = LocalDensity.current
        Column {
            if (lineTops.isNotEmpty()) {
                Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                    lineTops.forEachIndexed { index, top ->
                        LabelProjection(
                            contentModel = LabelContentModel(
                                index.toString()
                            ),
                            presentationModel = LabelPresentationModel(
                                textStyle = style
                            )
                        ).project(Modifier.offset(y = with(density) { top.toDp() }))
                    }
                }
            }
        }
        Column {
            //for now
            val textFieldValue = remember { mutableStateOf(initialText ?: TextFieldValue("")) }

            BasicTextField(
                value = textFieldValue.value,
                modifier = Modifier.fillMaxSize(),
                onValueChange = {
                    textFieldValue.value = it.copy(annotatedString = it.annotatedString)
                },
                onTextLayout = { result ->
                    lineTops = Array(result.lineCount) { result.getLineTop(it) }
                },
                textStyle = style
            )
        }
    }

}