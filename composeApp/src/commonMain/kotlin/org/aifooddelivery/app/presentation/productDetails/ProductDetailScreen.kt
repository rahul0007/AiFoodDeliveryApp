package org.aifooddelivery.app.presentation.productDetails
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.food_one
import aifooddeliveryapp.composeapp.generated.resources.food_two
import aifooddeliveryapp.composeapp.generated.resources.ic_cart_add_to
import aifooddeliveryapp.composeapp.generated.resources.ic_clocks
import aifooddeliveryapp.composeapp.generated.resources.ic_design_star_filled
import aifooddeliveryapp.composeapp.generated.resources.ic_doller
import aifooddeliveryapp.composeapp.generated.resources.ic_minus
import aifooddeliveryapp.composeapp.generated.resources.ic_pluse
import aifooddeliveryapp.composeapp.generated.resources.img_burger
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.contanerColor
import org.aifooddelivery.app.theme.lightBg
import org.aifooddelivery.app.theme.lightBlack
import org.aifooddelivery.app.theme.onPrimaryLight
import org.aifooddelivery.app.theme.primaryLight
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class ProductDetailScreen(rootNavController: NavHostController) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinInject<ProductDetailViewModel>()
        val state = viewModel.uiState.collectAsState()
        ProductDetailScreenContent(
            state = state.value,
            onBack = { /* handle back */ },
            onIncrease = viewModel::increaseQuantity,
            onDecrease = viewModel::decreaseQuantity
        )
    }
}

@Composable
fun ProductDetailScreenContent(
    state: ProductDetailUiState,
    onBack: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item { HeaderSection(onBack) }
            item { ContentHeader() }
            item { SectionTitle("Recommended For You", "See All") }
            item { Spacer(Modifier.height(15.dp)) }
        }

        BottomSection(
            quantity = state.quantity,
            onIncrease = onIncrease,
            onDecrease = onDecrease,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderSection(onBack: () -> Unit) {
    val images = listOf(
        Res.drawable.img_burger,
        Res.drawable.food_one,
        Res.drawable.food_two
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))// a bit taller for better spacing
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // ✅ Icon row WITH status bar padding
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
                    .padding(6.dp)
                    .clickable { onBack() }
            )
            Text(
                text = "About This Menu",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(6.dp)
            )
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
                    .padding(6.dp)
            )
        }

        // 🟦 Pager Indicator (dots)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .width(if (pagerState.currentPage == index) 24.dp else 12.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (pagerState.currentPage == index) primaryLight else onPrimaryLight
                        )
                )
            }
        }

        // Optional: You can also overlay a title like "About This Menu"

    }
}


@Composable
fun ContentHeader() {
    Spacer(Modifier.height(4.dp))
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Burger With Meat 🍔", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = blackColor
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "$12,230", fontSize = 24.sp, color = primaryLight, fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(10.dp))

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier.padding(horizontal = 2.dp).clip(RoundedCornerShape(8.dp))
                .background(lightBg)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoItem(painterResource(Res.drawable.ic_doller), "Free Delivery")
                InfoItem(painterResource(Res.drawable.ic_clocks), "20 - 30")
                InfoItem(painterResource(Res.drawable.ic_design_star_filled), "4.5")
            }
        }

        Spacer(Modifier.height(12.dp))
        Divider(
            color = contanerColor, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(Modifier.height(12.dp))

        Text("Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = blackColor)
        Text(
            "Burger With Meat is a typical food from our restaurant that is much in demand by many people, this is very recommended for you.",
            fontSize = 16.sp,
            color = lightBlack,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun BottomSection(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Quantity control
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_minus),
                contentDescription = "Decrease Quantity",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onDecrease() }
            )
            Spacer(Modifier.width(10.dp))
            Text(quantity.toString(), fontSize = 18.sp)
            Spacer(Modifier.width(10.dp))
            Image(
                painter = painterResource(Res.drawable.ic_pluse),
                contentDescription = "Increase Quantity",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onIncrease() }
            )
        }

        Spacer(Modifier.width(16.dp))

        // Add to Cart Button
        Button(
            onClick = { /* TODO: Add to cart logic */ },
            modifier = Modifier
                .height(50.dp)
                .weight(1.5f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_cart_add_to),
                    contentDescription = "Add to Cart",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Add to Cart", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String, action: String) {

    Spacer(Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(action, fontSize = 16.sp, color = Color(0xFFFF9800), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun InfoItem(icon: Painter, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = icon, contentDescription = null, modifier = Modifier.size(15.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(text, color = lightBlack, fontSize = 16.sp)
    }
}

