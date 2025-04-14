package com.poly.herewego.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.poly.herewego.R
import com.poly.herewego.data.Passport
import com.poly.herewego.presentation.widgets.Title
import com.poly.herewego.ui.component.MyProfile
import com.poly.herewego.ui.component.PassportWidget
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.theme.AppTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(onProfileClick: () -> Unit, onCategoryClick: (category: Passport) -> Unit, onSettingsClick: () -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.home_animation))
    var openAlertDialog by remember { mutableStateOf(false) }

    if (openAlertDialog) {
        MinimalDialog({ openAlertDialog = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Title("Home")
            Chip(onClick = onSettingsClick) {
                Icon(Icons.Rounded.Settings, contentDescription = "icon settings")
                Text("Settings")
            }
        }
//            DestinationDiscovery("A la une")
        MyProfile("", "\"Voir mon profil \uD83D\uDD0E", onProfileClick)
        Box(Modifier.height(12.dp))
        Row {
            Card(onClick = { openAlertDialog = true }) {
                Text("Voir tout les lieux que j'ai visités \uD83D\uDDFA\uFE0F", modifier = Modifier.padding(12.dp))
            }
        }
        Box(Modifier.height(24.dp))
        MyPassports("Mes passeports", viewModel.uiState.collectAsState().value, onCategoryClick)
//            Box(Modifier.height(12.dp))
//            Row {
//                Card(onClick = {}) {
//                    Text("Voir tout les passports", modifier = Modifier.padding(12.dp))
//                }
//            }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les passeports")
        }
        Box(Modifier.height(24.dp))
//            FriendAndStats("Mes amis")
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
fun MyPassports(title: String, passportList: List<Passport>, onCategoryClick: (data: Passport) -> Unit) {
    Title(title)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items = passportList, itemContent = { PassportWidget(it, it.name, it.color, it.icon, onCategoryClick) })
    }
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
        Box(
            Modifier
                .height(256.dp)
                .fillMaxWidth()
        )
    }
    Box(Modifier.height(24.dp))
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        HomeScreen({}, {}, {})
    }
}
