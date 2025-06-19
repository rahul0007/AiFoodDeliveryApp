package org.aifooddelivery.app.presentation.login
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ilustration_success
import aifooddeliveryapp.composeapp.generated.resources.password_changed
import aifooddeliveryapp.composeapp.generated.resources.password_changed_success
import aifooddeliveryapp.composeapp.generated.resources.verify_account
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Job
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.HeaderMediumStyle
import org.aifooddelivery.app.presentation.componets.InfoTextStyle
import org.aifooddelivery.app.presentation.componets.commonButtonTextStyle
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChangedPasswordBottomSheet(
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(Res.drawable.ilustration_success),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(Res.string.password_changed),
            style = HeaderMediumStyle,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(Res.string.password_changed_success),
            style = InfoTextStyle,
            color = grayColor
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onContinueClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(40.dp), // Match Login/ForgotPassword style
            colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
        ) {
            Text(
                text = stringResource(Res.string.verify_account),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = commonButtonTextStyle
            )
        }
    }
}
