package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.isDirectory


@Composable
fun  SidePanel(
    directoryChosen: String,
    currentFile: MutableState<Path?>
) {
    val lazyListState = rememberLazyListState()
    val selectedDirectoryAndChildren = mutableStateListOf<Path>()
    LaunchedEffect(directoryChosen) {
        withContext(Dispatchers.IO) {
            val curPath = Path.of(directoryChosen)
            selectedDirectoryAndChildren.add(curPath)
            Files
                .newDirectoryStream(Path.of(directoryChosen))
                .forEach {
                    selectedDirectoryAndChildren.add(it)
                }
        }

    }
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(top=3.dp, bottom = 3.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(
            items = selectedDirectoryAndChildren,
        ) {
            if(it.isDirectory()) {
                DirectoryChild(it)
            } else {
                FileChild(it) {
                    currentFile.value = it
                }
            }
        }
    }

}

