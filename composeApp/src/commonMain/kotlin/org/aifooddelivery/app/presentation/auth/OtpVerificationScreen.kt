package org.aifooddelivery.app.presentation.auth

import cafe.adriel.voyager.core.screen.Screen

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.clock_ic
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.aifooddelivery.app.showToast
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.CommonAppBar
import org.aifooddelivery.app.presentation.componets.HeaderLargeTextStyle
import org.aifooddelivery.app.presentation.componets.InfoTextStyle
import org.aifooddelivery.app.presentation.componets.OtpInput
import org.aifooddelivery.app.presentation.componets.commonButtonTextStyle
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class OtpVerificationScreen(
    private val email: String
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: OtpVerificationViewModel = koinInject()
        val otp by viewModel.otp.collectAsState()
        OtpVerificationScreenContent(
            email = email,
            otp = otp,
            onOtpChange = viewModel::onOtpChange,
            onContinueClick = {
                if (viewModel.isOtpValid()) {
                    AppNavigator.navigate(ResetPasswordScreen())
                } else {
                    showToast("Please enter 4-digit OTP")
                }
            }
        )
    }
}

@Composable
fun OtpVerificationScreenContent(
    email: String,
    otp: String,
    onOtpChange: (String) -> Unit,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CommonAppBar({}, "OTP")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Email verification", style = HeaderLargeTextStyle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Enter the verification code we sent you on: $email",
            textAlign = TextAlign.Start, style = InfoTextStyle
        )
        Spacer(modifier = Modifier.height(35.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OtpInput(length = 4) { onOtpChange(it) }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Didn't receive code?", color = grayColor)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Resend",
                color = primaryLight,
                modifier = Modifier.clickable { /* TODO */ },
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.clock_ic),
                contentDescription = "Icon",
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "09:00", color = grayColor)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onContinueClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = otp.length == 4,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
        ) {
            Text(
                "Continue",
                modifier = Modifier.padding(vertical = 8.dp),
                style = commonButtonTextStyle
            )
        }
    }
}
