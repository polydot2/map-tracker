package com.poly.herewego.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.poly.herewego.R
import com.poly.herewego.ui.component.AnimatedBook
import com.poly.herewego.ui.component.MyProfile
import com.poly.herewego.ui.component.PassportWidget
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.theme.AppTypography

@Composable
fun HomeScreen(onProfileClick: () -> Unit, onCategoryClick: (category: String) -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.home_animation))
    Column {
        Box() {
//            Image(
//                painter = painterResource(R.drawable.bb),
//                contentScale = ContentScale.Crop,
//                contentDescription = "background",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 24.dp)
//                    .height(132.dp)
//            )
//            Box(
//                Modifier
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(
//                                Color.White,
//                                Color.White,
//                                Color.Transparent
//                            )
//                        )
//                    )
//                    .padding(start = 128.dp)
//                    .width(128.dp)
//                    .height(132.dp)
//                    .align(Alignment.BottomStart)
//            )
            Box(
                Modifier
                    .background(
                        color = Color.White
                    )
                    .width(128.dp)
                    .fillMaxWidth()
//                    .height(132.dp)
                    .align(Alignment.BottomStart)
            )
            Box(Modifier.align(Alignment.BottomStart)) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(128.dp)
                        .offset(0.dp, 28.dp)
                )
            }
//            Box(
//                Modifier
//                    .background(
//                        color = Color.Black
//                    )
//                    .height(1.dp)
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
                .padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {
//            DestinationDiscovery("A la une")
            MyProfile("", "\"Voir mon profil \uD83D\uDD0E", onProfileClick)
//            LastDestination("Dernière destination")
            MyPassports("Mes voyages", onCategoryClick)
//            FriendAndStats("Mes amis")
            AnimatedBook()
        }
    }
}

@Composable
fun LastDestination(title: String) {
    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2196F3)
        ),
        border = BorderStroke(4.dp, Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically
        ) {
            Box(Modifier.width(12.dp))
            Text("Nantes", style = AppTypography.headlineMedium.copy(color = Color.White))
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(color = Color.White, shape = CircleShape)
                )
                Icon(Icons.Rounded.ArrowForward, contentDescription = "icon arrow right")
            }
        }
    }
    Box(Modifier.height(24.dp))
}

@Composable
fun MyPassports(title: String, onCategoryClick: (data: String) -> Unit) {
    var data = listOf(
        Pair("Nantes", Pair(0xFF00BCD4, "\uD83D\uDC18")),
        Pair("Paris", Pair(0xFF3F78B5, "\uD83D\uDDFC")),
        Pair("Lille", Pair(0xFF009688, "\uD83D\uDC35")),
        Pair("Lyon", Pair(0xFFFF9800, "\uD83C\uDF72")),
        Pair("Besançon", Pair(0xFF198112, "\uD83C\uDFF0"))
    )

    var test = Color(0xFF3F78B5)

    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
    Box(Modifier.height(12.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items = data, itemContent = { PassportWidget(it.first, it.second.first, it.second.second, onCategoryClick) })
    }
    Box(Modifier.height(24.dp))
}

@Composable
fun DestinationDiscovery(title: String) {
    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
    Box(Modifier.height(12.dp))
    Card {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(color = Color.Red, shape = CircleShape)
            )
            Box(Modifier.width(12.dp))
            Column {
                Text("Mon profil", style = AppTypography.headlineMedium)
                Text("Mini explorer \uD83D\uDD7A")
            }
        }
    }
    Box(Modifier.height(24.dp))
}

@Composable
fun FriendAndStats(title: String) {
    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
    Box(Modifier.height(12.dp))
    Card {
        Box(Modifier
            .height(256.dp)
            .fillMaxWidth())
    }
    Box(Modifier.height(24.dp))
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        HomeScreen({}, {})
    }
}
