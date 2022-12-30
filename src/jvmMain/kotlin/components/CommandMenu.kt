package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment

@Composable
fun BoxScope.CommandMenu(
    visibility: MutableState<Boolean>,
    options: List<Pair<String, () -> Unit>>
) {
    //Maybe refactor into Surface one day
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        if (visibility.value) {

        }
    }

}