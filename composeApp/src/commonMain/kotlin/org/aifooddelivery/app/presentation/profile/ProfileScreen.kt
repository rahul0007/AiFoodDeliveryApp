package org.aifooddelivery.app.presentation.profile
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.ic_camera
import aifooddeliveryapp.composeapp.generated.resources.img_user
import aifooddeliveryapp.composeapp.generated.resources.profile_side_arrow
import aifooddeliveryapp.composeapp.generated.resources.sign_out_ic
import aifooddeliveryapp.composeapp.generated.resources.vc_another_account
import aifooddeliveryapp.composeapp.generated.resources.vc_card
import aifooddeliveryapp.composeapp.generated.resources.vc_delete
import aifooddeliveryapp.composeapp.generated.resources.vc_help
import aifooddeliveryapp.composeapp.generated.resources.vc_person
import aifooddeliveryapp.composeapp.generated.resources.vc_settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.contanerColor
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.redColor
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.aifooddelivery.app.utils.DataStoreManager
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.launch
import org.aifooddelivery.app.decodeToImageBitmap
import org.aifooddelivery.app.presentation.auth.login.LoginScreen
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.AppNavigator.resetTo
import org.aifooddelivery.app.rememberImagePicker
import org.aifooddelivery.app.utils.ImagePickerBottomSheet

@Composable
fun ProfileScreen(rootNavController: NavController) {
    var showImagePicker by remember { mutableStateOf(false) }
    var selectedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val scope = rememberCoroutineScope()


    val imagePicker = rememberImagePicker()
    var showDialog by remember { mutableStateOf(false) }
    val dataStoreManager = remember { getKoin().get<DataStoreManager>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HomeToolbar(
            false,
            callBackButton = { /* handle back navigation if needed */ },
            title = "Profile Settings"
        )

        // 🔽 Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // ✅ enables scrolling
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ...Your existing content here (no changes inside)...

            // Profile Image + Info
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(90.dp)) {
                    if (selectedImageBitmap != null) {
                            Image(
                                bitmap = selectedImageBitmap!!,
                                contentDescription = "Selected Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                            )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.img_user),
                            contentDescription = "Default Profile Picture",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                        )
                    }

                    Image(
                        painter = painterResource(Res.drawable.ic_camera),
                        contentDescription = "Camera",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(30.dp)
                            .background(Color.White, CircleShape).clickable {
                                showImagePicker=true
                            }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text("Albert Stevano Bajefski", fontWeight = FontWeight.Bold)
                Text("Albertstevano@gmail.com", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // My Orders Card (no changes)
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().clickable {
                    rootNavController.navigate("delivery_tracking_screen")
                },
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp), // Increase if shadow not visible
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("My Orders", fontWeight = FontWeight.Bold)
                        Text("See All", color = Color(0xFFFF8C00))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Order ID 888333777", fontWeight = FontWeight.Bold)
                        Text(
                            "In Delivery", color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFFFF8C00), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = contanerColor, thickness = 2.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.food_one),
                            contentDescription = "Burger Image",
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Burger With Meat")
                            Text("$12,230", fontWeight = FontWeight.Bold, color = Color(0xFFFF8C00))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text("14 items", color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = contanerColor, thickness = 2.dp)

            // Profile Options Section
            Column {
                Text("Profile", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = grayColor)
                ProfileOptionItem("Personal Data", Res.drawable.vc_person, rootNavController)
                ProfileOptionItem("Settings", Res.drawable.vc_settings, rootNavController)
                ProfileOptionItem("Extra Card", Res.drawable.vc_card, rootNavController)

                Spacer(modifier = Modifier.height(12.dp))
                Text("Support", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = grayColor)
                ProfileOptionItem("Help Center", Res.drawable.vc_help, rootNavController)
                ProfileOptionItem(
                    "Request Account Deletion",
                    Res.drawable.vc_delete,
                    rootNavController
                )
                ProfileOptionItem(
                    "Add another account",
                    Res.drawable.vc_another_account,
                    rootNavController
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,           // background
                        contentColor = Color.Transparent       // text/icon color
                    ),
                    border = BorderStroke(1.dp, grayColor)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.sign_out_ic),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Sign Out",
                        color = redColor,
                        fontSize = 14.sp,
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

    if (showImagePicker) {
        ImagePickerBottomSheet(
            onCameraClick = {
                scope.launch {
                    val result = imagePicker.captureImageWithCamera()
                    result?.let {
                        selectedImageBitmap= decodeToImageBitmap(it.bytes)
                    }
                }
            },
            onGalleryClick = {
                scope.launch {
                    val result = imagePicker.pickImageFromGallery()
                    result?.let {
                        selectedImageBitmap= decodeToImageBitmap(it.bytes)
                    }
                }
            },
            onDismiss = { showImagePicker = false }
        )

    }

    if (showDialog) {
        LogoutDialog(
            onDismiss = { showDialog = false },
            onLogout = {
                showDialog = false
                scope.launch {
                    dataStoreManager.setLoginCompleted(completed = false)
                    resetTo(LoginScreen())
                    AppNavigator.navigate(LoginScreen())
                }

            }
        )
    }
}

@Composable
fun ProfileOptionItem(title: String, icon: DrawableResource, rootNavController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable {
                when (title) {
                    "Personal Data" -> {
                        rootNavController.navigate("personal_data_screen")
                    }

                    "Settings" -> {
                        rootNavController.navigate("settings_screen")
                    }

                    "Help Center" -> {
                        rootNavController.navigate("help_center_screen")
                    }

                    "Extra Card" -> {
                        rootNavController.navigate("extra_card")
                    }
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = blackColor,
            fontWeight = FontWeight.Bold
        )
        Image(painter = painterResource(Res.drawable.profile_side_arrow), contentDescription = null)
    }
}


