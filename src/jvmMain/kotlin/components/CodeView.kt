package components

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import components.textarea.TextArea
import jetbrains
import structs.Code
import structs.ThemeMode
import structs.themes.HeliumTheme


@Composable
fun CodeView(
    mode: ThemeMode,
    theme: HeliumTheme,
    code: Code,
) {
    var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
    val shouldUpdateLines = remember { derivedStateOf { lineTops.isEmpty() } }
    val scrollState = rememberScrollState()
    Box(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .verticalScroll(scrollState)
        ) {
            LineNumberList(lineTops)
            TextArea(
                code,
                codeTheme = theme.syntaxHighLighting(mode),
                style = TextStyle(
                    fontFamily = jetbrains(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) { result ->
                if (shouldUpdateLines.value) {
                    lineTops = Array(result.lineCount) { result.getLineTop(it) }
                }
            }
        }
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(scrollState),
            Modifier.align(Alignment.CenterEnd).fillMaxHeight().width(20.dp)
        )
    }
}