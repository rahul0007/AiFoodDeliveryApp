package org.aifooddelivery.app.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.aifooddelivery.app.data.model.PaymentCard

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.apple_pay
import aifooddeliveryapp.composeapp.generated.resources.ic_card_bg
import aifooddeliveryapp.composeapp.generated.resources.ic_readit
import aifooddeliveryapp.composeapp.generated.resources.mastercard
import aifooddeliveryapp.composeapp.generated.resources.mastercard_log
import aifooddeliveryapp.composeapp.generated.resources.paypal
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExtraCardScreen(rootNavController: NavHostController) {
    val cards = listOf(
        PaymentCard("MasterCard", "**** 0783 7873", Color(0xFFFB8C00), Res.drawable.mastercard_log),
        PaymentCard("Paypal", "**** 0582 4672", Color.Gray, Res.drawable.paypal),
        PaymentCard("Apple Pay", "**** 0582 4672", Color.Gray, Res.drawable.apple_pay),
    )
    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            Text("Extra Card", style = MaterialTheme.typography.titleMedium)
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier.clickable {
                    showDialog = true
                })
        }

        Spacer(Modifier.height(35.dp))

        // SoCard Display
        SoCardDisplay()

        Spacer(Modifier.height(24.dp))

        Text("Credit card", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(Modifier.height(12.dp))

        // Card List
        /* cards.forEachIndexed { index, card ->
             PaymentCardItem(card, isHighlighted = index == 0)
             Spacer(Modifier.height(12.dp))
         }*/


        Column {
            cards.forEachIndexed { index, card ->
                PaymentCardItem(
                    card = card,
                    isHighlighted = index == selectedIndex,
                    onClick = { selectedIndex = index }
                )
                Spacer(Modifier.height(20.dp))
            }
        }

        Spacer(Modifier.height(32.dp))

        // Add New Card Button
        Button(
            onClick = { rootNavController.navigate("add_card") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(30.dp))
        ) {
            Text("Add New Card", color = Color.White)
        }
    }

    if (showDialog) {
        DeleteDialog(
            onDismiss = { showDialog = false },
            onDelete = {
                showDialog = false
                scope.launch {
                    showDialog = false
                }

            }
        )
    }
}

@Composable
fun SoCardDisplay() {
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape) // Clip applies to both image and background
    ) {
        // Background Image
        Image(
            painter = painterResource(Res.drawable.ic_card_bg), // Replace with your image
            contentDescription = "Card Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
        )

        // Foreground content
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("SoCard", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(35.dp))
            Text(
                "••••  ••••  ••••  8374",
                color = Color.White,
                fontSize = 32.sp,

                )
            Spacer(Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Card holder name", color = Color.White, fontSize = 14.sp)
                    Text("•••• ••••", color = Color.White, fontSize = 16.sp)
                }
                Column {
                    Text("Expiry date", color = Color.White, fontSize = 16.sp)
                    Text("••/••", color = Color.White, fontSize = 14.sp)
                }

                Column {
                    Image(
                        painter = painterResource(Res.drawable.mastercard),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 48.dp, height = 32.dp)
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
        }
    }
}

@Composable
fun PaymentCardItem(
    card: PaymentCard,
    isHighlighted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = if (isHighlighted) Color(0xFFFF9800) else Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White)
            .clickable { onClick() } // handle selection
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.ic_readit),
                    modifier = Modifier.size(15.dp),
                    contentDescription = null
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(card.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(card.number, color = Color.Gray, fontSize = 14.sp)
                }
            }
            Image(
                painter = painterResource(card.logoRes),
                contentDescription = "${card.title} Logo",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}
