package org.aifooddelivery.app.presentation.search

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.cat_burgger
import aifooddeliveryapp.composeapp.generated.resources.cat_drink
import aifooddeliveryapp.composeapp.generated.resources.cat_pizza
import aifooddeliveryapp.composeapp.generated.resources.cat_taco
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.ic_design_star_filled
import aifooddeliveryapp.composeapp.generated.resources.ic_fiter
import aifooddeliveryapp.composeapp.generated.resources.icon_new_search
import aifooddeliveryapp.composeapp.generated.resources.location_ic
import aifooddeliveryapp.composeapp.generated.resources.no_result
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.data.model.Category
import org.aifooddelivery.app.theme.deviderColor
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchScreen(rootNavController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val results = remember(searchQuery) {
        ""
    }
    var showNoResult = results.isEmpty() && searchQuery.isNotBlank()

    val categories = listOf(
        Category("Burger", Res.drawable.cat_burgger),
        Category("Juice", Res.drawable.cat_taco),
        Category("Drink", Res.drawable.cat_drink),
        Category("Pizza", Res.drawable.cat_pizza)
    )
    val recentSearches = listOf("Burgers", "Fast food", "Dessert", "French", "Pastry")
    var selectedCategory by remember { mutableStateOf("Burger") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        // Top Bar
        HomeToolbar(true, {
            rootNavController.popBackStack()
        }, "Settings")
        Spacer(modifier = Modifier.height(12.dp))
        // Search Field

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    if (searchQuery.isEmpty()) {
                        showNoResult = false
                    }
                },
                singleLine = true,
                placeholder = { Text("Search Food") },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        showNoResult = true
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_fiter),
                        contentDescription = null, modifier = Modifier.size(24.dp)
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.icon_new_search), // Replace with your actual icon
                        contentDescription = null, modifier = Modifier.size(24.dp)
                    )
                },

                )

            Spacer(modifier = Modifier.height(12.dp))

            if (!showNoResult) {
                SearchCategorySection(categories, selectedCategory, {
                    selectedCategory = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                // Recent Searches
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Recent searches", fontWeight = FontWeight.Bold)
                    Text("Delete", color = primaryLight, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                recentSearches.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.icon_new_search),
                            contentDescription = null, modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(item, modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = deviderColor,
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                // My recent orders
                Text("My recent orders", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                repeat(2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.food_one),
                            contentDescription = null,
                            modifier = Modifier.height(100.dp).width(100.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Ordinary Burgers", fontWeight = FontWeight.Bold)
                            Text("Burger Restaurant", fontSize = 12.sp, color = Color.Gray)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_design_star_filled),
                                    contentDescription = null,
                                    tint = Color(0xFFFFA500),
                                    modifier = Modifier.size(14.dp)
                                )
                                Text("4.9", fontSize = 12.sp, color = Color.Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    painter = painterResource(Res.drawable.location_ic),
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text("190m", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            } else {
                if (showNoResult) {
                    NoResultView()
                }
            }


        }

    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFFF9800) else Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(category.iconRes) , // Add image to resources
            contentDescription = null,
            modifier = Modifier.height(40.dp).width(40.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.name,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else grayColor
        )
    }
}

@Composable
fun NoResultView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.no_result),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "We couldn't find any result!",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Please check your search for any typos or spelling errors,\nor try a different search term.",
            color = Color.Gray,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

// Add missing drawable resource aliases to match your Res.drawable references
