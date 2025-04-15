package com.poly.herewego.presentation.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.poly.herewego.data.Passport
import com.poly.herewego.presentation.widgets.Medal
import com.poly.herewego.presentation.widgets.Title
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.component.PassportWidget
import com.poly.herewego.ui.component.PlaceItem
import com.poly.herewego.ui.utils.hexStringToColor
import kotlin.random.Random

@Composable
fun PassportScreen(
    passportId: String,
    onPoiClick: (place: String) -> Unit,
    viewModel: PassportViewModel = hiltViewModel()
) {
    val passportState by viewModel.uiState.collectAsState()
    val isFavorite by viewModel.isFavorite

    LaunchedEffect(passportId) {
        viewModel.fetchData(passportId)
    }

    when (val state = passportState) {
        is PassportUiState.Loading -> CircularProgressIndicator()
        is PassportUiState.Success -> PassportSuccess(state.passport, onPoiClick, { viewModel.toggleFavorite() }, isFavorite)
        is PassportUiState.Error -> Text("Passport not found")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PassportSuccess(
    passport: Passport,
    onPoiClick: (place: String) -> Unit,
    onToggleFavorite: () -> Unit,
    isFavorite: Boolean,
) {
    val maxImageHeight = 250.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Image de fond
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxImageHeight),
            model = passport.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "${passport.name} illustration",
        )

        // LazyColumn pour le contenu scrollable
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = (maxImageHeight - 50.dp)) // Décalage pour chevaucher l'image
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Row(Modifier.fillMaxWidth())
                    {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Title(passport.name, 0.dp)
                            Chip(onClick = onToggleFavorite) {
                                Icon(Icons.Rounded.Star, contentDescription = "icon settings")
                                Text(if (isFavorite) "Retirer des favoris" else "Ajouter aux favoris")
                            }
                            Text("5 / ${passport.places.size} sites visités")
                            Spacer(Modifier.height(12.dp))
                            Row {
                                Medal(passport.color.hexStringToColor())
                            }
                        }
                        PassportWidget(passport, passport.name, passport.color, passport.icon, {}, true)
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Liste des points d'intérêt",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            // Liste des points d'intérêt
            items(passport.places) { place ->
                PlaceItem(
                    name = place.name,
                    icon = passport.icon,
                    passportName = passport.name,
                    color = passport.color,
                    isVisited = Random.Default.nextBoolean(),
                    onClick = onPoiClick
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    HistoryWidget()
                    Spacer(Modifier.height(12.dp))
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun HistoryWidget() {
    Column {
        Text(
            text = "Historique",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text("Check elephant le 12/04/2025")
        Text("Check elephant le 15/05/2025")
    }
}

@Preview
@Composable
fun PreviewScroll() {
    val passports = listOf(
        Passport("id", "date", "Nantes", "0xFF00BCD4", "\uD83D\uDC18", 0f, 0f, "", listOf()),
        Passport("id", "date", "Nantes", "0xFF3F78B5", "\uD83D\uDDFC", 0f, 0f, "", listOf())
    )

    AppTheme {
        PassportSuccess(passports[0], {}, {}, false)
    }
}

@Preview
@Composable
fun PreviewScroll2() {
    val passports = listOf(
        Passport("id", "date", "Nantes", "0xFF00BCD4", "\uD83D\uDC18", 0f, 0f, "", listOf()),
        Passport("id", "date", "Nantes", "0xFF3F78B5", "\uD83D\uDDFC", 0f, 0f, "", listOf())
    )

    AppTheme {
        PassportSuccess(passports[1], {}, {}, true)
    }
}

