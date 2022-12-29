package components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.helium.scalable.svg.code_plus
import com.helium.scalable.svg.folders
import com.helium.scalable.svg.folders_off

@Composable
fun LeftPanelCommands() {
    Row(Modifier
        .border(1.dp, Color.Blue)
        .fillMaxWidth()
    ) {
       Box(
           Modifier
               .clickable(onClick = { println("hello") },role = Role.Button)
               .padding(PaddingValues(2.dp))
       ) {
           Icon(code_plus(), null,Modifier.size(25.dp))
       }
       Box(
           Modifier
               .clickable(onClick = {println("hello")},role = Role.Button)
               .padding(PaddingValues(2.dp))
       ) {
           Icon(folders_off(),  null,Modifier.size(25.dp))
       }
       Box(
           Modifier
               .clickable(onClick = {println("hello")}, role = Role.Button)
               .padding(PaddingValues(2.dp))
       ) {
           Icon(folders(), null, Modifier.size(25.dp))
       }
    }

}