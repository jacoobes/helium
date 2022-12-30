package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.helium.scalable.svg.code_plus
import com.helium.scalable.svg.folders
import com.helium.scalable.svg.folders_off
import components.iconbuttons.HoverableFlatIconButton

@Composable
fun LeftPanelCommands() {
    Row(
        Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val (iconSize, setIconSize) = remember { mutableStateOf(20.dp) }

        Box(
            Modifier
                .clickable(onClick = { println("hello") }, role = Role.Button)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(PaddingValues(2.dp))

        ) {
            Icon(code_plus(), null, Modifier.size(iconSize).fillMaxSize(), tint = MaterialTheme.colorScheme.onSecondary)
        }
        Box(
            Modifier
                .clickable(onClick = { println("hello") }, role = Role.Button)
                .padding(PaddingValues(2.dp))
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                folders_off(),
                null,
                Modifier.size(iconSize).fillMaxSize(),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
        Box(
            Modifier
                .clickable(onClick = { println("hello") }, role = Role.Button)
                .padding(PaddingValues(2.dp))
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(folders(), null, Modifier.size(iconSize).fillMaxSize(), tint = MaterialTheme.colorScheme.onSecondary)
        }
    }

}