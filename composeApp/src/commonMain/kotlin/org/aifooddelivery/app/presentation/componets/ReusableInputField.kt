    package org.aifooddelivery.app.presentation.componets

    import aifooddeliveryapp.composeapp.generated.resources.Res
    import aifooddeliveryapp.composeapp.generated.resources.password
    import aifooddeliveryapp.composeapp.generated.resources.visibility
    import aifooddeliveryapp.composeapp.generated.resources.visibility_off
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.OutlinedTextFieldDefaults
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.input.PasswordVisualTransformation
    import androidx.compose.ui.text.input.VisualTransformation
    import androidx.compose.ui.unit.dp
    import org.jetbrains.compose.resources.painterResource
    import org.jetbrains.compose.resources.stringResource

    @Composable
    fun ReusableInputField(
        label: String,
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        isError: Boolean,
        errorText: String?,
        keyboardType: KeyboardType
    ) {
        Text(label)
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red, // optional
                focusedLabelColor = Color.Black, // optional
                unfocusedLabelColor = Color.Black // optional
            )
        )
        errorText?.let {
            Text(
                it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }