package org.aifooddelivery.app.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.data.model.Category
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.search.CategoryItem

@Composable
fun CategorySection(
    categories: List<Category>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Find by Category",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = blackColor
            )
            Text(
                text = "See All",
                color = primaryLight,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* Handle click */ }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categories.take(4).forEach { category ->
                Box(modifier = Modifier.weight(1f).clickable {
                    onCategorySelected(category.name)
                }) {
                    CategoryItem(
                        category = category,
                        isSelected = category.name == selectedCategory,
                        onClick = { onCategorySelected(category.name) }
                    )
                }
            }
        }
    }
}



