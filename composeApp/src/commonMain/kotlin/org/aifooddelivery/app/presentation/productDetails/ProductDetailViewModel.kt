package org.aifooddelivery.app.presentation.productDetails

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.aifooddelivery.app.data.model.FoodItem
import org.koin.core.component.KoinComponent


class ProductDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    fun increaseQuantity() {
        _uiState.value = _uiState.value.copy(quantity = _uiState.value.quantity + 1)
    }

    fun decreaseQuantity() {
        val newQuantity = (_uiState.value.quantity - 1).coerceAtLeast(1)
        _uiState.value = _uiState.value.copy(quantity = newQuantity)
    }
}
