package components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import json
import kotlinx.serialization.encodeToString
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import structs.Settings


@Composable
fun AuroraApplicationScope.SettingsEditor(
    settings: MutableState<Settings>,
    skin: AuroraSkinDefinition,
    show: MutableState<Boolean>
) {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.CenterEnd),
        size = DpSize((settings.value.dimensions.width * .50).dp, (settings.value.dimensions.height * .50).dp)
    )
    val parser = remember { PrettifyParser() }
    val themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
    val theme = remember(themeState) { themeState.theme }
    val code = json.encodeToString(settings.value)
    val (vis, setVis) = show
    if(vis) {
        AuroraWindow(
            state = state,
            skin = skin,
            title = "Settings",
            resizable = true,
            windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
            onCloseRequest = { setVis(false) }
        ) {
            Row {
                TextArea(
                    settings,
                    TextFieldValue(
                        annotatedString = parseCodeAsAnnotatedString(
                            parser = parser,
                            theme = theme,
                            lang = CodeLang.JSON,
                            code = code,
                        )
                    ),
                )
            }
        }
    }
}