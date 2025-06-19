package org.aifooddelivery.app.presentation.productDetails

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.food_four
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.food_three
import aifooddeliveryapp.composeapp.generated.resources.food_two
import org.aifooddelivery.app.data.model.FoodItem

data class ProductDetailUiState(
    val product: FoodItem = FoodItem("Burger", Res.drawable.food_one, 4.9, "190m", "$17,230", true),
    val recommended: List<FoodItem> = getRecommendedItems(),
    val quantity: Int = 1
)
fun getRecommendedItems(): List<FoodItem> = listOf(
    FoodItem("Ordinary Burgers", Res.drawable.food_one, 4.9, "190m", "$17,230", true),
    FoodItem("Burger With Meat", Res.drawable.food_two, 4.9, "190m", "$17,230", false),
    FoodItem("Burger With Meat", Res.drawable.food_three, 4.9, "190m", "$17,230", false),
    FoodItem("Burger With Meat", Res.drawable.food_four, 4.9, "190m", "$17,230", false)
)