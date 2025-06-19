package org.aifooddelivery.app.presentation.order

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.back
import aifooddeliveryapp.composeapp.generated.resources.cart_add
import aifooddeliveryapp.composeapp.generated.resources.cart_menu
import aifooddeliveryapp.composeapp.generated.resources.cart_min
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.food_two
import aifooddeliveryapp.composeapp.generated.resources.ic_delete_cart
import aifooddeliveryapp.composeapp.generated.resources.ic_discount
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.aifooddelivery.app.data.model.CartItem
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.contanerColor2
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.grayColorBorder
import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.home.FoodCard
import org.aifooddelivery.app.presentation.productDetails.getRecommendedItems
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(rootNavController: NavController) {
    var promoCode by rememberSaveable { mutableStateOf("") }

    val cartItems = remember {
        mutableStateListOf(
            CartItem("Burger With Meat", "$12,230", Res.drawable.food_one),
            CartItem("Ordinary Burgers", "$12,230", Res.drawable.food_two),


            )
    }

    Box(modifier = Modifier.background(color = contanerColor2)) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = contanerColor2)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            TopAppBar {
                rootNavController.popBackStack()
                println("on Cart back screen")
            }
            Spacer(Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contanerColor2),
                contentPadding = PaddingValues(
                    bottom = 20.dp,
                    start = 3.dp,
                    end = 3.dp
                ), // optional extra space
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {


                item { DeliveryLocationSection() }

                item {
                    PromoCodeInputField(promoCode, { promoCode = it }, {})
                }

                items(cartItems) { item ->
                    CartItemRow(item)
                }

                item {
                    SectionTitle("Recommended For You", "See All")
                    Spacer(Modifier.height(15.dp))
                }

                gridItems(2, getRecommendedItems()) { item ->
                    FoodCard(item = item) {
                        rootNavController.navigate("product_detail_screen")
                    }
                }

                item {
                    PaymentSummarySection(
                        totalItems = 3,
                        deliveryFee = "Free",
                        discount = "-$10,900",
                        total = "$38,000"
                    )
                }

                item {
                    Button(
                        onClick = { rootNavController.navigate("payment_screen") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9900)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Order Now", color = Color.White)
                    }
                }


            }
        }
    }
}

@Composable
fun TopAppBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            println("onback_invoce")
            onBack.invoke()
        }) {
            Image(
                painter = painterResource(Res.drawable.back),
                modifier = Modifier.size(35.dp), // ✅ No clickable here
                contentDescription = "Back"
            )
        }

        Text("My Cart", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(Res.drawable.cart_menu),
                modifier = Modifier.size(35.dp), // ✅ No clickable here
                contentDescription = "Menu"
            )
        }
    }
}

@Composable
fun DeliveryLocationSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Delivery Location", fontSize = 16.sp, color = grayColor)
            Text("Home", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = blackColor)
        }
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, primaryLight),
        ) {
            Text("Change Location", color = primaryLight, fontSize = 14.sp)
        }
    }
}

@Composable
fun PromoCodeInputField(
    promoCode: String,
    onPromoCodeChange: (String) -> Unit,
    onApplyClick: () -> Unit
) {
    Box(modifier = Modifier.padding(vertical = 15.dp)) {
        OutlinedTextField(
            value = promoCode,
            onValueChange = onPromoCodeChange,
            placeholder = {
                Text("Promo Code. . .", color = grayColor)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_discount), // Replace with your actual icon
                    contentDescription = null,
                    tint = primaryLight
                )
            },
            trailingIcon = {
                Button(
                    onClick = onApplyClick,
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9900),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(35.dp)
                        .padding(end = 10.dp) // smaller height to fit inside the text field
                ) {
                    Text("  Apply  ")
                }
            },
            shape = RoundedCornerShape(50),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFFE0E0E0),
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Red
            )
        )
    }
}


@Composable
fun CartItemRow(item: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Outer margin
        colors = CardDefaults.cardColors(containerColor = Color.White), // Background color
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), // Inner content padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomCheckbox()

            Image(
                painter = painterResource(item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)

            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.price,
                    color = Color(0xFFFF9900),
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    QuantitySelector()

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {}) {
                        Image(
                            painter = painterResource(Res.drawable.ic_delete_cart),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomCheckbox() {
    var checked by remember { mutableStateOf(true) }
    Checkbox(
        checked = checked,
        onCheckedChange = { checked = it }, // Update state on toggle
        colors = CheckboxDefaults.colors(
            checkedColor = primaryLight,       // Checked box color
            uncheckedColor = Color.Gray,            // Unchecked box border color
            checkmarkColor = Color.White            // Color of the checkmark
        )
    )
}

@Composable
fun QuantitySelector() {
    var quantity by remember { mutableStateOf(1) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (quantity > 1) quantity--

        }) {
            Image(
                painter = painterResource(Res.drawable.cart_min),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )

        }
        Text(
            quantity.toString(),
            modifier = Modifier.padding(horizontal = 8.dp),
            color = blackColor, fontSize = 20.sp
        )

        IconButton(onClick = {
            quantity++
        }) {
            Image(
                painter = painterResource(Res.drawable.cart_add),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        }

    }
}

@Composable
fun PaymentSummarySection(totalItems: Int, deliveryFee: String, discount: String, total: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = grayColorBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White) // Inner background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(10.dp))
            Text(
                "Payment Summary",
                fontSize = 18.sp,
                color = blackColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            SummaryRow("Total Items ($totalItems)", "$48,900")
            Spacer(Modifier.height(10.dp))
            SummaryRow("Delivery Fee", deliveryFee)
            Spacer(Modifier.height(10.dp))
            SummaryRow("Discount", discount, Color.Red)
            Spacer(Modifier.height(10.dp))
            Spacer(Modifier.height(10.dp))
            SummaryRow("Total", total, Color.Black, true)
            Spacer(Modifier.height(10.dp))

        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, color: Color = Color.Black, bold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontSize = 18.sp, color = grayColor, fontWeight = FontWeight.Bold)
        Text(
            value,
            fontSize = 18.sp,
            color = blackColor,
            fontWeight = FontWeight.Bold
        )
    }
}

fun <T> LazyListScope.gridItems(
    columns: Int, items: List<T>, itemContent: @Composable (T) -> Unit
) {
    val rows = if (items.size % columns == 0) items.size / columns else items.size / columns + 1
    items(rows) { rowIndex ->
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            for (columnIndex in 0 until columns) {
                val itemIndex = rowIndex * columns + columnIndex
                if (itemIndex < items.size) {
                    Box(Modifier.weight(1f)) {
                        itemContent(items[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
fun SectionTitle(title: String, action: String) {

    Spacer(Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth().padding(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(action, fontSize = 16.sp, color = Color(0xFFFF9800), fontWeight = FontWeight.Bold)
    }
}