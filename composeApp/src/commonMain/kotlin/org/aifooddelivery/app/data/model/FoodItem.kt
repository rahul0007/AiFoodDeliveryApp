package org.aifooddelivery.app.data.model

import org.jetbrains.compose.resources.DrawableResource

data class FoodItem(
    val title: String,
    val imageRes: DrawableResource,
    val rating: Double,
    val distance: String,
    val price: String,
    var isFavorite:Boolean
)