package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import utils.blend

/**
 * starting point for the side panel
 */
@Composable
fun SidePanelColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val background = MaterialTheme.colorScheme.surface
    val surface = MaterialTheme.colorScheme.secondary
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(blend(background,surface,.10f))
            .then(modifier),
        content = content
    )
}