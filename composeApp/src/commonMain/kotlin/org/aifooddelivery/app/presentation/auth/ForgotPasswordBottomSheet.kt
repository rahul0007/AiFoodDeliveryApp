package org.aifooddelivery.app.presentation.auth

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.btn_continue
import aifooddeliveryapp.composeapp.generated.resources.email_ic
import aifooddeliveryapp.composeapp.generated.resources.forgot_password
import aifooddeliveryapp.composeapp.generated.resources.select_which_contact_details
import aifooddeliveryapp.composeapp.generated.resources.send_via_email
import aifooddeliveryapp.composeapp.generated.resources.send_via_whatsApp
import aifooddeliveryapp.composeapp.generated.resources.whats_app_ic
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.ContactOptionCard
import org.aifooddelivery.app.presentation.componets.HeaderMediumStyle
import org.aifooddelivery.app.presentation.componets.InfoMediumTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ForgotPasswordBottomSheet(
    email: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.forgot_password),
            style = HeaderMediumStyle,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(Res.string.select_which_contact_details),
            color = Color.Black,
            style = InfoMediumTextStyle
        )

        Spacer(modifier = Modifier.height(24.dp))

        ContactOptionCard(
            icon = painterResource(Res.drawable.whats_app_ic),
            title = stringResource(Res.string.send_via_whatsApp),
            detail = "+12 8347 2838 28", // Consider passing this from ViewModel if it's dynamic
            isSelected = selectedOption == "whatsapp",
            onClick = { onOptionSelected("whatsapp") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ContactOptionCard(
            icon = painterResource(Res.drawable.email_ic),
            title = stringResource(Res.string.send_via_email),
            detail = email,
            isSelected = selectedOption == "email",
            onClick = { onOptionSelected("email") }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onContinueClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(40.dp), // match `LoginScreen` styling
            colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
        ) {
            Text(
                text = stringResource(Res.string.btn_continue),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
