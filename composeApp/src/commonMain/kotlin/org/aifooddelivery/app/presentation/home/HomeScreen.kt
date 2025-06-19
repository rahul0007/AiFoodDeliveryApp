package org.aifooddelivery.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.aifooddelivery.app.data.model.Category
import org.aifooddelivery.app.data.model.FoodItem
import org.aifooddelivery.app.theme.contanerColor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(rootNavController: NavController, ) {
    var viewModel: HomeViewModel = koinViewModel()
    val selectedCategory =viewModel.selectedCategory.collectAsState()

    HomeScreenContent(
        rootNavController = rootNavController,
        categories = viewModel.categories,
        selectedCategory = selectedCategory.value,
        foodItems = viewModel.foodItems,
        onCategorySelected = viewModel::onCategorySelected,
        onFoodSelected = { rootNavController.navigate("product_detail_screen") }
    )
}
@Composable
fun HomeScreenContent(
    rootNavController: NavController,
    categories: List<Category>,
    selectedCategory: String,
    foodItems: List<FoodItem>,
    onCategorySelected: (String) -> Unit,
    onFoodSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contanerColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderSection(rootNavController)
        Spacer(modifier = Modifier.height(5.dp))
        CategorySection(categories, selectedCategory, onCategorySelected)
        Spacer(modifier = Modifier.height(5.dp))
        FoodGrid(foodItems, onFoodSelected)
        Spacer(modifier = Modifier.height(10.dp))
    }
}