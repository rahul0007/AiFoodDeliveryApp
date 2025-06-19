package org.aifooddelivery.app.presentation.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.otpColor

@Composable
fun OtpInput(
    length: Int = 4,
    onOtpComplete: (String) -> Unit
) {

    val otpValues = remember { List(length) { mutableStateOf("") } }
    val focusRequesters = remember { List(length) { FocusRequester() } }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 0 until length) {
            val previousValue = otpValues[i].value
            OutlinedTextField(
                value = otpValues[i].value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && (newValue.isEmpty() || newValue.all { it.isDigit() })) {
                        otpValues[i].value = newValue

                        when {
                            newValue.isNotEmpty() -> {
                                if (i < length - 1) {
                                    focusRequesters[i + 1].requestFocus()
                                } else {
                                    focusManager.clearFocus()
                                }
                            }

                            newValue.isEmpty() && previousValue.isNotEmpty() -> {
                                if (i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            }
                        }

                        val currentOtp = otpValues.joinToString("") { it.value }
                        onOtpComplete(currentOtp)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(65.dp)
                    .focusRequester(focusRequesters[i]),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                ),
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = otpColor,
                    unfocusedBorderColor = otpColor,
                    disabledBorderColor = otpColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
            if (i < length - 1) {
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}




