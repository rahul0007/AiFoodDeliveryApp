package org.aifooddelivery.app.data.model

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class PaymentCard(val title: String, val number: String, val borderColor: Color, val logoRes: DrawableResource)
