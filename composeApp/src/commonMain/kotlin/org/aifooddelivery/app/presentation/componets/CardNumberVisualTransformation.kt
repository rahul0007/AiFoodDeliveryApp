import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(16)

        val formatted = buildString {
            trimmed.forEachIndexed { index, c ->
                append(c)
                if ((index + 1) % 4 == 0 && index != 15) {
                    append(" / ")
                }
            }
        }

        val maxTransformedLength = formatted.length
        val maxOriginalLength = trimmed.length

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Calculate how many separators have been added before this offset
                val groups = offset / 4
                val adjusted = offset + groups * 3
                return adjusted.coerceAtMost(maxTransformedLength)
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Determine how many separators have been passed in the transformed string
                val adjusted = when {
                    offset <= 7 -> offset
                    offset <= 14 -> offset - 3
                    offset <= 21 -> offset - 6
                    else -> offset - 9
                }
                return adjusted.coerceAtMost(maxOriginalLength)
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}
