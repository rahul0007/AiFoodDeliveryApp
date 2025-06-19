package org.aifooddelivery.app.presentation.componets

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.email_address
import aifooddeliveryapp.composeapp.generated.resources.enter_email
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommonTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isReadOnly: Boolean = false,
    visualTransformations: VisualTransformation? = null
) {
    Column(modifier = modifier) {

        Text(hint)
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            readOnly = isReadOnly,
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            keyboardActions = KeyboardActions.Default,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation= visualTransformations ?: VisualTransformation.None

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

fun setVisual(visualTransformations: VisualTransformation?): VisualTransformation? {
    if(visualTransformations!=null)
    {
        return visualTransformations
    }else return null
}
