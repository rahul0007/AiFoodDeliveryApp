package org.aifooddelivery.app.presentation.componets
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.back_arrow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.blackColor
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    callBackButton: () -> Unit,
    title: String,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            Icon(
                painter = painterResource(Res.drawable.back_arrow),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(25.dp) // Keep size same as action icon
                    .clickable { callBackButton() }
            )
        },
        title = {
            Text(
                text = title,
                fontSize = 25.sp,
                color = blackColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            // Same size invisible icon to balance the layout
            Icon(
                painter = painterResource(Res.drawable.back_arrow),
                contentDescription = null,
                tint = Color.Transparent,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
            )
        }
    )
}



