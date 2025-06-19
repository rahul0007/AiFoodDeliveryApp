package org.aifooddelivery.app.presentation.profile

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.back_arrow
import aifooddeliveryapp.composeapp.generated.resources.profile_side_arrow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.theme.uncheckdSwich
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(rootNavController: NavHostController) {
    var isPushNotificationEnabled by remember { mutableStateOf(false) }
    var isLocationEnabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        // Top App Bar

        HomeToolbar(true, {}, "Settings")
        Spacer(modifier = Modifier.height(24.dp))
        // Section: Profile

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "PROFILE",
                color = grayColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            SettingToggleRow("Push Notification", isPushNotificationEnabled) {
                isPushNotificationEnabled = it
            }

            SettingToggleRow("Location", isLocationEnabled) {
                isLocationEnabled = it
            }

            SettingTextRow("Language", "English") {
               showBottomSheet=true
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section: Other
            Text(
                text = "OTHER",
                color = grayColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            SettingArrowRow("About Ticketis") { }
            SettingArrowRow("Privacy Policy") { }
            SettingArrowRow("Terms and Conditions") { }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            SelectLanguageBottomSheet("en",
                onSelect = {
                    scope.launch {
                        sheetState.hide()
                        showBottomSheet = false
                    }
                }, onSubmit = {

                }
            )
        }
    }
}

@Composable
fun SettingTextRow(title: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title,color = blackColor,  fontSize = 18.sp)
        Row {
            Text(value, color = blackColor, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(Res.drawable.profile_side_arrow),
                contentDescription = "Next",
            )
        }
    }
}

@Composable
fun SettingArrowRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 18.sp, color = blackColor)
        Image(
            painter = painterResource(Res.drawable.profile_side_arrow),
            contentDescription = "Next",
        )
    }
}

@Composable
fun SettingToggleRow(title: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = blackColor, fontSize = 18.sp)
        Switch(
            checked = checked,
            modifier = Modifier.scale(0.8f),
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = primaryLight, // orange
                uncheckedTrackColor = uncheckdSwich,
                uncheckedThumbColor = Color.White
            )
        )
    }
}
