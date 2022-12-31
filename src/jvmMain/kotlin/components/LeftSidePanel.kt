package components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.io.File


@Composable
fun LeftSidePanel() {
    val lazyListState = rememberLazyListState()
    val stateSelection = remember { mutableStateOf<File?>(null) }
    val fillerData = remember { mutableStateOf(emptyList<Int>()) }
    LazyColumn(
        modifier = Modifier
    ) {
        item {
            FileNode()
            FileNode()
            FileNode()
        }
    }
}

