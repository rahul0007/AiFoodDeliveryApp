package org.aifooddelivery.app.presentation.profile
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.back_arrow
import aifooddeliveryapp.composeapp.generated.resources.codes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.aifooddelivery.app.data.model.LanguageOption
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.blackColor

@Composable
fun SelectLanguageBottomSheet(
    selectedLanguageCode: String,
    onSelect: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val languages = listOf(
        LanguageOption("Indonesia", "id", selectedLanguageCode == "id"),
        LanguageOption("English (US)", "en", selectedLanguageCode == "en"),
        LanguageOption("Thailand", "th", selectedLanguageCode == "th"),
        LanguageOption("Chinese", "zh", selectedLanguageCode == "zh")
    )

    var selectedCode by remember { mutableStateOf(selectedLanguageCode) }

    Column  (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Drag handle

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Select Language",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        languages.forEach { lang ->
            LanguageOptionItem(
                language = lang.copy(isSelected = lang.code == selectedCode),
                onClick = {
                    selectedCode = lang.code
//                    onSelect(lang.code)
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Select")
        }
    }
}
@Composable
fun LanguageOptionItem(language: LanguageOption, onClick: () -> Unit) {
    val borderColor = if (language.isSelected) Color(0xFFFF9800) else Color.LightGray
    val flagPlaceholder = painterResource(Res.drawable.codes) // Replace with actual flag image if needed

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row {

        }
        Image(
            painter = flagPlaceholder,
            contentDescription = "Flag",
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = language.name,
            fontSize = 16.sp,
            color = blackColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        if (language.isSelected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Selected",
                tint = Color(0xFFFF9800)
            )
        }
    }
}

