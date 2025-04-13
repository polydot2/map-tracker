package com.poly.herewego.presentation.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.poly.herewego.ui.theme.AppTheme

import androidx.compose.foundation.lazy.items
import com.poly.herewego.ui.component.PassportWidget

@Composable
fun CityDetailScreen(onPoiClick: (place: String) -> Unit) {
    // État pour gérer la hauteur de l'image
    val maxImageHeight = 300.dp // Hauteur maximale de l'image
    val density = LocalDensity.current
    val maxImageHeightPx = with(density) { maxImageHeight.toPx() }

    // État pour suivre le décalage de l'image
    var imageHeightOffsetPx by remember { mutableStateOf(0f) }

    var list = listOf(Pair("Le Lieu unique", false), Pair("Chateau des ducs", true), Pair("Elephant \uD83D\uDC18", false), Pair("Jardin des plantes", true))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Image de la ville
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    maxImageHeight + 50.dp
                )
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(Color.Gray) // Placeholder pour l'image
                .zIndex(0f) // Image en arrière-plan
        ) {
            // Placeholder : texte centré pour simuler l'image
            Text(
                text = "Illustration de la ville",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Contenu scrollable avec LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Spacer pour positionner le contenu sous l'image initialement
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxImageHeight)
                )
            }

            // Contenu de la ville
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "Nantes",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text("5 / 14 sites visités")
                        }

                        PassportWidget("Nantes", 0xFF00BCD4, "\uD83D\uDC18", {}, true)
                    }

                    Box(Modifier.height(12.dp))
                    Text(
                        text = "Liste des points d'interets",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            items(list.size) { index ->
                PlaceItem(list[index].first, list[index].second, onPoiClick)
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    HistoryWidget()
                    // Texte Lorem Ipsum
                    Text(
                        text = """
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor
                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                            irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                            deserunt mollit anim id est laborum.
                            
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor
                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                            irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                            deserunt mollit anim id est laborum.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 24.sp
                    )

                    // Padding entre le Lorem Ipsum et la LazyColumn
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Padding en bas pour éviter que le contenu ne soit coupé
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewScroll() {
    AppTheme {
        CityDetailScreen {}
    }
}

