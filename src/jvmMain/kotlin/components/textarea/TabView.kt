package components.textarea

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TabView(selectedTab: Int) {
    TabRow(
        selectedTabIndex = selectedTab,
        tabs = {
            Tab(false, {}, text = {Text("asdf")})
            Tab(false, {}, text = {Text("asdf")})
            Tab(false, {}, text = {Text("asdf")})
            Tab(false, {}, text = {Text("asdf")})
        }
    )
}