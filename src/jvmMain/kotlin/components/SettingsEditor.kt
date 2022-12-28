package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.helium.scalable.svg.alpha
import com.helium.scalable.svg.beta
import com.helium.scalable.svg.dimensions
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import json
import kotlinx.serialization.encodeToString
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import structs.Settings


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuroraApplicationScope.SettingsEditor(
    settings: Settings,
    skin: MutableState<AuroraSkinDefinition>,
    show: MutableState<Boolean>
) {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize((settings.dimensions.width * .50).dp, (settings.dimensions.height * .50).dp)
    )
    val (vis, setVis) = show
    if(vis) {
        AuroraWindow(
            state = state,
            skin = skin.value,
            title = "Settings",
            resizable = true,
            windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
            onPreviewKeyEvent = {
                 if(it.isCtrlPressed && it.key == Key.W) {
                     setVis(false)
                 }
                false
            },
            onCloseRequest = { setVis(false) }
        ) {
            Row {
                Box {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {
                        LabelProjection(
                            contentModel = LabelContentModel("editorFont"),
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel("codeFont")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel("dimensions")
                        ).project()
                        AuroraSkinSwitcher(
                                onSkinChange = { skin.value = it },
                                popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart
                        )
                    }
//                    Column(
//                        modifier = Modifier.fillMaxWidth(.5f)
//                    ) {
//                        CommandButtonPanelProjection(
//                            contentModel = CommandPanelContentModel(
//                                commandGroups = listOf(
//                                    CommandGroup(
//                                        commands = listOf(
//                                            Command("", icon=alpha(), action = {}),
//                                            Command("", icon=beta(),action = {}),
//                                            Command("", icon = dimensions(), action = {}),
//                                        )
//                                    )
//                                )
//                            ),
//                            presentationModel = CommandPanelPresentationModel(
//                                layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Fixed(1)),
//                                commandHorizontalAlignment = HorizontalAlignment.Center,
//                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
//                                commandPresentationState = CommandButtonPresentationState.TileFitToIcon,
//                                isMenu = true
//                            )
//                        ).project(
//                            Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                    }
                }
            }


            //
//            {
//                "editorFont" : "JetBrainsMono.ttf",
//                "codeFont" : "JetBrainsMono.ttf",
//                "dimensions" : {
//                "width" : 1000,
//                "height" : 900
//            },
//                "theme" : "poo"
//            }


        }
    }
}