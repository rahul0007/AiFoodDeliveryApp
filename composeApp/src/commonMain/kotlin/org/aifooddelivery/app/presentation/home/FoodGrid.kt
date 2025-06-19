package org.aifooddelivery.app.presentation.home

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.food_four
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.food_three
import aifooddeliveryapp.composeapp.generated.resources.food_two
import aifooddeliveryapp.composeapp.generated.resources.heart
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.data.model.FoodItem
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.primaryLight
import org.jetbrains.compose.resources.painterResource

@Composable
fun FoodGrid(onFoodSelected: List<FoodItem>, onFoodSelected1: () -> Unit) {

    val foodItems = listOf(
        FoodItem("Ordinary Burgers", Res.drawable.food_one, 4.9, "190m", "$17,230", true),
        FoodItem("Burger With Meat", Res.drawable.food_two, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_three, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_four, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_one, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_two, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_three, 4.9, "190m", "$17,230", false),

        )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(foodItems) { item ->

            Box(modifier = Modifier.padding(8.dp)) {
                FoodCard(item, onFoodSelected1)
            }
        }
    }
}

@Composable
fun FoodCard(item: FoodItem,onFoodSelected: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth().clickable {
                onFoodSelected.invoke()
            }
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )

                IconButton(
                    onClick = { item.isFavorite = !item.isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(25.dp) // Smaller circle
                            .background(Color.White.copy(alpha = 0.8f), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = if (item.isFavorite)
                                painterResource(Res.drawable.heart)
                            else
                                painterResource(Res.drawable.heart),
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier.size(14.dp) // Tiny icon inside small circle
                        )
                    }
                }
            }
            Text(item.title, fontSize = 18.sp,fontWeight = FontWeight.Bold,
                color = blackColor
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Nested Row to align icon and text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(14.dp) // Optional: control icon size
                    )
                    Spacer(modifier = Modifier.width(4.dp)) // Small gap between icon and text
                    Text(
                        text = "${item.rating}",
                        fontSize = 14.sp // Ensure consistent size
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(14.dp) // Optional: control icon size
                    )
                    Spacer(modifier = Modifier.width(4.dp)) // Small gap between icon and text
                    Text(
                        text = item.distance,
                        fontSize = 14.sp // Ensure consistent size
                    )
                }


            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                item.price,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = primaryLight
            )
        }
    }
}
