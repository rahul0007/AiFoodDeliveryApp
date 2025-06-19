package org.aifooddelivery.app.presentation.profile

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.delivered
import aifooddeliveryapp.composeapp.generated.resources.food_ready
import aifooddeliveryapp.composeapp.generated.resources.img_user
import aifooddeliveryapp.composeapp.generated.resources.map_placeholder
import aifooddeliveryapp.composeapp.generated.resources.order_placed
import aifooddeliveryapp.composeapp.generated.resources.out_for_delivery
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.grayColor

@Composable
fun DeliveryTrackingScreen(onDismiss: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {

        // 🗺️ Map Background (you can replace with actual Map)
        Image(
            painter = painterResource(Res.drawable.map_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(top = 48.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable { /* handle back */ }
                    .padding(8.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Delivered your order",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // 📦 Bottom Sheet-style card anchored to bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column() {
                    // Rider Info
                    Box(
                        modifier = Modifier.padding(16.dp)
                            .clip(RoundedCornerShape(36.dp))
                            .background(color = Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.img_user),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    "Cristopert Dastin",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text("ID 213752", fontSize = 12.sp, color = grayColor)
                            }

                            // Message and Call Buttons
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Default.Email,
                                        contentDescription = "Chat",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .background(Color(0xFFFFA500), shape = CircleShape)
                                            .padding(6.dp)
                                            .size(24.dp)
                                    )
                                }
                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Default.Call,
                                        contentDescription = "Call",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .background(Color(0xFFFFA500), shape = CircleShape)
                                            .padding(6.dp)
                                            .size(24.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                    Column(
                        modifier = Modifier.background(color = Color.White)
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Spacer(Modifier.height(16.dp))
                        Text("Your Delivery Time", color = Color.Gray)
                        Text("Estimated 8:30 - 9:15 PM", fontWeight = FontWeight.Bold)

                        Spacer(Modifier.height(16.dp))

                        // Timeline Icons
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(
                                Res.drawable.order_placed,
                                Res.drawable.food_ready,
                                Res.drawable.out_for_delivery,
                                Res.drawable.delivered
                            ).forEachIndexed { index, iconRes ->
                                Image(
                                    painter = painterResource(iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                if (index < 3) {
                                    Spacer(
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(1.dp)
                                            .background(Color(0xFFFFA500))
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Order", color = Color.Gray)
                        Text("2 Burger With Meat", fontSize = 16.sp)
                        Text("$283", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }

        }
    }

}