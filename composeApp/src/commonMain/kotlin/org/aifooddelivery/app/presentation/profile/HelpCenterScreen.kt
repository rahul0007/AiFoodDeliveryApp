package org.aifooddelivery.app.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ic_agent
import aifooddeliveryapp.composeapp.generated.resources.ic_buyer
import aifooddeliveryapp.composeapp.generated.resources.ic_genral
import aifooddeliveryapp.composeapp.generated.resources.ic_sellers
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.aifooddelivery.app.presentation.componets.SearchBar
import org.jetbrains.compose.resources.painterResource

@Composable
fun HelpCenterScreen(rootNavController: NavHostController) {
    val categories = listOf(
        HelpCategory(
            "General",
            "Basic question about Restate",
            Color(0xFF4F46E5),
            painterResource(Res.drawable.ic_genral)
        ),
        HelpCategory(
            "Sellers",
            "All you need to know about selling your home to Restate",
            Color(0xFFFF9800),
            painterResource(Res.drawable.ic_sellers)
        ),
        HelpCategory(
            "Buyers",
            "Everything you need to know about buying with Restate",
            Color(0xFFEF4444),
            painterResource(Res.drawable.ic_buyer)
        ),
        HelpCategory(
            "Agents",
            "How buying agents and listing agents can work with Restate",
            Color(0xFF3B82F6),
            painterResource(Res.drawable.ic_agent)
        )
    )
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Top App Bar

        HomeToolbar(true, {}, "Help Center")
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(24.dp))
            Text(
                "Hi, how we can help you?",
                color = blackColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))

            // Search box
            SearchBar(
                query = searchQuery,
                onQueryChanged = { searchQuery = it }
            )

            Spacer(Modifier.height(35.dp))
            // Category cards
            categories.forEach { category ->
                HelpCategoryItem(category)
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun HelpCategoryItem(category: HelpCategory) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row() {
            Icon(
                painter = category.icon,
                contentDescription = null,
                tint = category.iconColor,
                modifier = Modifier.size(25.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column {
                Text(category.title, fontWeight = FontWeight.Bold, fontSize = 21.sp)
                Spacer(Modifier.height(10.dp))
                Text(category.description, fontSize = 17.sp, color = Color.Gray)
            }
        }
    }


}

data class HelpCategory(
    val title: String,
    val description: String,
    val iconColor: Color,
    val icon: Painter
)
