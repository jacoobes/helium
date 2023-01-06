package components.lazytree

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import components.SidePanelColumn

@Composable
fun NoFiles() {
    SidePanelColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource("scalable/sad.svg"), null)
        Text("No Files?", style = MaterialTheme.typography.bodyLarge)
    }
}