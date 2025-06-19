package org.aifooddelivery.app.presentation.componets
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardNumberField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    placeholder: String = "1234 5678 9012 3456",
    errorText:String
) {
    Column(modifier = modifier) {

        Text(hint)
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { input ->
                val digits = input.filter { it.isDigit() }
                if (digits.length <= 16) {
                    onValueChange(digits)
                }
            },
            placeholder = { Text(placeholder) },
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = CardNumberVisualTransformation(),
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )

        if (isError && errorText != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }
}

fun formatCardNumber(input: String): String {
    return buildString {
        input.take(16).forEachIndexed { index, c ->
            append(c)
            if ((index + 1) % 4 == 0 && index != input.lastIndex) {
                append(' ')
            }
        }
    }
}

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.replace(" ", "").take(16)
        val formatted = buildString {
            trimmed.forEachIndexed { index, c ->
                append(c)
                if ((index + 1) % 4 == 0 && index != trimmed.lastIndex) {
                    append(' ')
                }
            }
        }

        // Map from original index to transformed (with spaces) index
        val offsetMap = mutableListOf<Int>()
        var extraSpaceCount = 0

        for (i in trimmed.indices) {
            offsetMap.add(i + extraSpaceCount)
            if ((i + 1) % 4 == 0 && i != trimmed.lastIndex) {
                extraSpaceCount++
            }
        }

        val transformed = formatted
        val maxTransformedLength = transformed.length

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offsetMap.getOrNull(offset)?.coerceAtMost(maxTransformedLength)
                    ?: maxTransformedLength
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Remove spaces to compute approximate position
                var count = 0
                var digitsSeen = 0
                while (count < offset && count < transformed.length) {
                    if (transformed[count] != ' ') digitsSeen++
                    count++
                }
                return digitsSeen.coerceAtMost(trimmed.length)
            }
        }

        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}
