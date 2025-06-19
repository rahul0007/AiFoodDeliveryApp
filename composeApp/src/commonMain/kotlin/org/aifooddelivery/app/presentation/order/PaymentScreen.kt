package org.aifooddelivery.app.presentation.order

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.food_one
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.jetbrains.compose.resources.painterResource

@Composable
fun PaymentScreen(rootNavController: NavHostController) {

    Column {
        HomeToolbar(true, {
            rootNavController.popBackStack()
        }, "Payment")

        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
                .padding(horizontal = 20.dp, vertical = 24.dp).verticalScroll(rememberScrollState())
        ) {
            // Header


            Spacer(modifier = Modifier.height(16.dp))
            Text("You deserve better meal", color = grayColor, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))
            Text("Item Ordered", fontWeight = FontWeight.Bold, color = blackColor, fontSize = 18.sp)

            // Item row
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.food_one), // Replace with actual
                    contentDescription = null,
                    modifier = Modifier.size(90.dp).clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Burger With Meat", fontWeight = FontWeight.SemiBold)
                    Text("$ 12,230", color = Color(0xFFFF9900), fontWeight = FontWeight.Bold)
                }
                Text("14 items", color = grayColor, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Details Transaction", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            TransactionRow("Cherry Healthy", "$ 180.000")
            TransactionRow("Driver", "$ 50.000")
            TransactionRow("Tax 10%", "$ 80.390")
            TransactionRow("Total Price", "$ 100.000", isTotal = true)

            Divider(
                modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = grayColor
            )

            // Delivery Info
            Text("Deliver to :", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = blackColor)

            Spacer(modifier = Modifier.height(12.dp))
            DeliveryInfoItem("Name", "Albert Stevano")
            DeliveryInfoItem("Phone No.", "+12 8347 2838 28")
            DeliveryInfoItem("Address", "New York")
            DeliveryInfoItem("House No.", "BC54 Berlin")
            DeliveryInfoItem("City", "New York City")

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
                Button(
                    onClick = { /* TODO: Handle checkout */ },
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9900))
                ) {
                    Text("Checkout Now", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }

}

@Composable
fun TransactionRow(label: String, amount: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = if (isTotal) blackColor else grayColor, fontSize = 16.sp)
        Text(
            amount,
            color = if (isTotal) primaryLight else blackColor,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun DeliveryInfoItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label, color = grayColor, fontSize = 16.sp, modifier = Modifier.weight(1f)
        )
        Text(
            value,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            color = blackColor,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
}


