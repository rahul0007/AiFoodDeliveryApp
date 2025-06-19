package org.aifooddelivery.app.presentation.profile

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.arrowforward
        import aifooddeliveryapp.composeapp.generated.resources.email_address
import aifooddeliveryapp.composeapp.generated.resources.enter_email
import aifooddeliveryapp.composeapp.generated.resources.ic_camera
import aifooddeliveryapp.composeapp.generated.resources.ic_down
import aifooddeliveryapp.composeapp.generated.resources.img_user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.stringResource

@Composable
fun PersonalDataScreen(
    fullName: String = "Albert Stevano Bajefski",
    dob: String = "19/06/1999",
    gender: String = "Male",
    phone: String = "+1 325-433-7656",
    email: String = "albertstevano@gmail.com",
    onSaveClick: () -> Unit = {},
    rootNavController: NavHostController
) {
    var selectedGender by remember { mutableStateOf(gender) }
    var names by rememberSaveable { mutableStateOf(fullName) }
    var dobValue by rememberSaveable { mutableStateOf(dob) }
    var phoneValue by rememberSaveable { mutableStateOf(phone) }
    var emailValue by rememberSaveable { mutableStateOf(email) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Personal Data",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(Res.drawable.img_user),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .offset((-6).dp, (-6).dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFA500)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_camera),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(  modifier = Modifier.fillMaxWidth(),  // Use fillMaxWidth only here
            horizontalAlignment = Alignment.Start)
       {
            ProfileTextField(
                label = "Full Name",
                value = names,
                onValueChange = { names = it }
            )

            ProfileTextField(
                label = "Date of Birth",
                value = dobValue,
                onValueChange = { dobValue = it }
            )

            GenderDropdown(
                selected = selectedGender,
                onGenderSelected = { selectedGender = it }
            )

            ProfileTextField(
                label = "Phone",
                value = phoneValue,
                onValueChange = { phoneValue = it }
            )

            ProfileTextField(
                label = "Email",
                value = emailValue,
                onValueChange = { emailValue = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSaveClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Text("Save", color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Text(label)
    Spacer(Modifier.height(5.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions.Default,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
            // other colors
        )

    )

}
@Composable
fun GenderDropdown(
    selected: String,
    onGenderSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female", "Other")

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)) {

        // Label above the box (like your ProfileTextField)
        Text("Gender")
        Spacer(modifier = Modifier.height(5.dp))

        // Box styled like a non-editable input
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { expanded = true }
                .border(
                    width = 1.dp,
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selected.ifEmpty { "Select gender" },
                    color = if (selected.isEmpty()) Color.Gray else Color.Black
                )

                Image(
                    painter = painterResource(Res.drawable.ic_down),
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            genderOptions.forEach { gender ->
                DropdownMenuItem(
                    text = { Text(gender) },
                    onClick = {
                        onGenderSelected(gender)
                        expanded = false
                    }
                )
            }
        }
    }
}

