package org.aifooddelivery.app.presentation.profile

import CardNumberVisualTransformation
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ic_card_bg
import aifooddeliveryapp.composeapp.generated.resources.mastercard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.presentation.componets.CommonTextField
import org.aifooddelivery.app.presentation.componets.ExpiryDateField
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSummaryBottomSheet(
    name: String,
    cardNumber: String,
    expiry: String,
    cvv: String,
    onDismiss: () -> Unit
) {
    // Convert incoming values into mutable states (only if you intend to change them)
    var mutableCardNumber by remember { mutableStateOf(cardNumber) }
    var mutableExpiry by remember { mutableStateOf(expiry) }
    var mutableName by remember { mutableStateOf(name) }
    var mutableCvv by remember { mutableStateOf(cvv) }

    // Now use `mutableCardNumber`, `mutableExpiry`, etc. instead of immutable parameters

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            cardData(mutableCardNumber, mutableName, mutableExpiry)

            Spacer(Modifier.height(24.dp))

            CommonTextField(
                value = mutableName,
                onValueChange = { mutableName = it },
                hint = "Cardholder Name",
                placeholder = "Cardholder Name",
                isError = false,
                errorText = "Enter valid name",
                keyboardType = KeyboardType.Text
            )

            Spacer(Modifier.height(16.dp))

            CommonTextField(
                value = mutableCardNumber,
                onValueChange = { mutableCardNumber = it },
                hint = "Card Number",
                placeholder = "1234 5678 9012 3456",
                isError = false,
                errorText = "Enter 16-digit card number",
                keyboardType = KeyboardType.Number,
                visualTransformations = CardNumberVisualTransformation()
            )

            Spacer(Modifier.height(16.dp))


            Row(Modifier.fillMaxWidth()) {
                ExpiryDateField(
                    hint = "Expiry Date",
                    value = mutableExpiry,
                    onValueChange = { mutableExpiry = it },
                    isError = false,
                    placeholder = "MM / YY",
                    errorText = "Invalid or expired date",
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(16.dp))

                CommonTextField(
                    value = mutableCvv,
                    onValueChange = { mutableCvv = it },
                    hint = "3-Digit CVV",
                    placeholder = "3-Digit CVV",
                    isError = false,
                    errorText = "Enter 3-Digit CVV",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { onDismiss() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
            ) {
                Text("Done", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun cardData(cardNumber: String, name: String, expiry: String) {
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp) // Set fixed height for the card
            .clip(shape)
    ) {
        // Background Image
        Image(
            painter = painterResource(Res.drawable.ic_card_bg),
            contentDescription = "Card Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize() // Ensures it fills the Box
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("VISA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 35.sp)
            }

            Text(
                text = cardNumber.chunked(4).joinToString(" "), // 1234 5678 ...
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center // or TextAlign.Start / End as needed
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Card holder name",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                    Text(name, color = Color.White, fontWeight = FontWeight.Medium)
                }
                Column {
                    Text("Expiry Date", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                    Text(expiry, color = Color.White, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

