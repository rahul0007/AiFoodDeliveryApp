package org.aifooddelivery.app.presentation.home

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.cat_burgger
import aifooddeliveryapp.composeapp.generated.resources.cat_drink
import aifooddeliveryapp.composeapp.generated.resources.cat_pizza
import aifooddeliveryapp.composeapp.generated.resources.cat_taco
import aifooddeliveryapp.composeapp.generated.resources.food_four
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.food_three
import aifooddeliveryapp.composeapp.generated.resources.food_two
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.aifooddelivery.app.data.model.Category
import org.aifooddelivery.app.data.model.FoodItem

class HomeViewModel : ViewModel() {

    private val _selectedCategory = MutableStateFlow("Burger")
    val selectedCategory: StateFlow<String> = _selectedCategory
    val categories = listOf(
        Category("Burger", Res.drawable.cat_burgger),
        Category("Juice", Res.drawable.cat_taco),
        Category("Drink", Res.drawable.cat_drink),
        Category("Pizza", Res.drawable.cat_pizza)
    )

    val foodItems = listOf(
        FoodItem("Ordinary Burgers", Res.drawable.food_one, 4.9, "190m", "$17,230", true),
        FoodItem("Burger With Meat", Res.drawable.food_two, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_three, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_four, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_one, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_two, 4.9, "190m", "$17,230", false),
        FoodItem("Burger With Meat", Res.drawable.food_three, 4.9, "190m", "$17,230", false),
    )

    fun onCategorySelected(name: String) {
        _selectedCategory.value = name
    }
}
