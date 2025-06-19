package org.aifooddelivery.app.presentation.componets
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.primaryLight

@Composable
fun TermsAgreement(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {}
) {
    val termsTag = "TERMS"
    val privacyTag = "PRIVACY"

    val annotatedText = buildAnnotatedString {
        append("I Agree with ")

        pushStringAnnotation(tag = termsTag, annotation = "terms")
        withStyle(SpanStyle(color = primaryLight, fontWeight = FontWeight.Bold)) {
            append("Terms of Service")
        }
        pop()

        append(" and ")

        pushStringAnnotation(tag = privacyTag, annotation = "privacy")
        withStyle(SpanStyle(color = primaryLight, fontWeight = FontWeight.Bold)) {
            append("Privacy Policy")
        }
        pop()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange
            ),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            modifier = Modifier.size(20.dp),
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = primaryLight // orange color
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        ClickableText(
            text = annotatedText,
            style = TextStyle(color = Color.Black, textAlign = TextAlign.Start, fontSize = 16.sp),
            onClick = { offset ->
                annotatedText.getStringAnnotations(offset, offset).firstOrNull()?.let {
                    when (it.tag) {
                        termsTag -> onTermsClick()
                        privacyTag -> onPrivacyClick()
                    }
                }
            }
        )
    }
}
