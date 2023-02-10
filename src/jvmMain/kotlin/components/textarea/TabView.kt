package components.textarea

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import structs.Editor
import kotlin.io.path.name

@Composable
fun TabView(
    selectedTabIndex: MutableState<Int>,
    editors: SnapshotStateList<Editor>
) {
    Row {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            tabs = {
                for((idx, editor) in editors.withIndex()) {
                   Tab(selectedTabIndex.value == idx, onClick = {
                       selectedTabIndex.value = idx
                   }) {
                       Text(editor.code.path.name)
                   }
                }
            }
        )
    }

}