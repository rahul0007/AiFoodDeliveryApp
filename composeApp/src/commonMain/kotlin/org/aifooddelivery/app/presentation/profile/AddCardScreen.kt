package org.aifooddelivery.app.presentation.profile

import CardNumberVisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.presentation.componets.CardNumberField
import org.aifooddelivery.app.presentation.componets.CommonTextField
import org.aifooddelivery.app.presentation.componets.ExpiryDateField
import org.aifooddelivery.app.presentation.componets.isValidExpiry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(rootNavController: NavHostController) {

    var showBottom by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedCardType by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    // Error states
    var nameError by remember { mutableStateOf(false) }
    var cardNumberError by remember { mutableStateOf(false) }
    var expiryError by remember { mutableStateOf(false) }
    var cvvError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }
    var cardTypeError by remember { mutableStateOf(false) }

    val cardTypes = listOf("Visa", "MasterCard", "Amex")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text("Extra Card", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))

        CommonTextField(
            value = name,
            onValueChange = { name = it },
            hint = "Name on Card",
            placeholder = "Name on Card",
            isError = nameError,
            errorText = "Enter valid name",
            keyboardType = KeyboardType.Text
        )
        Spacer(Modifier.height(16.dp))

        CardNumberField(
            value = cardNumber, // raw digits only
            onValueChange = {
                val digits = it.filter { ch -> ch.isDigit() }
                if (digits.length <= 16) {
                    cardNumber = digits
                    cardNumberError = digits.length != 16
                }
            },
            hint = "Card Number",
            placeholder = " _ _ _ _  /  _ _ _ _  /  _ _ _ _  /  _ _ _ _ ",
            isError = cardNumberError,
            errorText = "Enter valid card number"
        )

        Spacer(Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            CommonTextField(
                isReadOnly = true,
                value = selectedCardType,
                onValueChange = { selectedCardType = it },
                hint = "Card Type",
                placeholder = "Card Type",
                isError = cardTypeError,
                errorText = "Select card type",
                keyboardType = KeyboardType.Text,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cardTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedCardType = type
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        ExpiryDateField(
            hint = "Expiry Date",
            value = expiry,
            onValueChange = { expiry = it },
            isError = expiryError,
            placeholder = "MM / YY",
            errorText = "Invalid or expired date"
        )

        Spacer(Modifier.height(16.dp))

        CommonTextField(
            value = cvv,
            onValueChange = { cvv = it },
            hint = "CVV",
            placeholder = "CVV",
            isError = cvvError,
            errorText = "Enter valid CVV",
            keyboardType = KeyboardType.Number
        )

        Spacer(Modifier.height(16.dp))

        CommonTextField(
            value = address,
            onValueChange = { address = it },
            hint = "Billing Address",
            placeholder = "Billing Address",
            isError = addressError,
            errorText = "Address is required",
            keyboardType = KeyboardType.Text
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                // Validate all fields
                nameError = name.trim().isEmpty()
                cardNumberError = cardNumber.filter { it.isDigit() }.length != 16
                expiryError = expiry.length != 4 || !isValidExpiry(expiry)
                cvvError = cvv.length !in 3..4
                addressError = address.trim().isEmpty()
                cardTypeError = selectedCardType.isEmpty()

                val hasError = listOf(
                    nameError, cardNumberError, expiryError,
                    cvvError, addressError, cardTypeError
                ).any { it }

                if (!hasError) {
                    // Proceed to save or next screen
                    println("Card saved successfully!")
                    showBottom = true
                }
            },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
        ) {
            Text("Save Card", color = Color.White)
        }
    }
    if (showBottom) {
        CardSummaryBottomSheet(
            name = name,
            cardNumber = cardNumber,
            expiry = expiry,
            cvv = cvv,
            onDismiss = { showBottom = false }
        )
    }
}
