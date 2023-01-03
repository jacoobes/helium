package components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.io.File
import java.util.*


@Composable
fun SidePanel(directoryChosen : String) {
    val lazyListState = rememberLazyListState()
    var selectedDirectoryAndChildren by remember { mutableStateOf<List<File>>(emptyList()) }
    val fillerData = remember { mutableStateOf(emptyList<Int>()) }
    LaunchedEffect(directoryChosen) {
        val dir = File(directoryChosen)

    }
    LazyColumn(
        modifier = Modifier
    ) {

    }

}

