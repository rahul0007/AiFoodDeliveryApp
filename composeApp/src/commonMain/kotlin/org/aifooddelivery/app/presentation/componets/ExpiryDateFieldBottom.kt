package org.aifooddelivery.app.presentation.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.getCurrentYearMonth

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping

@Composable
fun ExpiryDateFieldBottom(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    placeholder: String = "MM / YY",
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    errorText: String? = null,

) {
    Column {

        OutlinedTextField(
            value = value,
            onValueChange = {
                val digits = it.filter { c -> c.isDigit() }
                if (digits.length <= 4) {
                    onValueChange(digits)
                }
            },
            isError = isError || (value.length == 4 && !isValidExpiry(value)),
            singleLine = true,
            readOnly = readOnly,
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier,
            visualTransformation = ExpiryDateVisualTransformation() // 👈 fix display formatting & cursor issues
        )

        if ((isError || (value.length == 4 && !isValidExpiryBottom(value))) && errorText != null) {
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }
}


fun isValidExpiryBottom(input: String): Boolean {
    if (input.length != 4) return false

    val month = input.take(2).toIntOrNull() ?: return false
    val year = input.drop(2).toIntOrNull() ?: return false

    if (month !in 1..12) return false

    // Current year and month must be passed from platform-specific code
    val (currentYear, currentMonth) = getCurrentYearMonth() ?: return false

    // Convert 2-digit input year to 4-digit
    val fullYear = 2000 + year
    // Check expiry not before current year/month
    return (fullYear > currentYear) || (fullYear == currentYear && month >= currentMonth)
}

