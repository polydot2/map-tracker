package com.poly.herewego.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.theme.AppTypography
import kotlin.random.Random

@Composable
fun AllStamp(title: String) {
//    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
//    Box(Modifier.height(12.dp))
    Card {
        Box(
            Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(56.dp),
                modifier = Modifier.fillMaxSize().padding(12.dp)
            ) {
                items(10) {
                    val offsetX = Random.nextFloat() * 10
                    val offsetY = Random.nextFloat() * 10
                    val rotation = Random.nextFloat() * 30 - 15 // Rotation entre -15° et 15°
//                AsyncImage(
//                    model = "https://picsum.photos/200/200",
//                    contentDescription = "Translated description of what the image contains",
//                    modifier = Modifier
//                        .offset(offsetX.dp, offsetY.dp) // Position aléatoire
//                        .size(size) // Taille aléatoire
//                        .graphicsLayer(
//                            rotationZ = rotation // Rotation aléatoire
//                        )
//                )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(56.dp)
                            .offset(offsetX.dp, offsetY.dp)
                            .background(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()), shape = CircleShape)
                            .graphicsLayer(rotationZ = rotation)
                    )
                }
            }
        }
        Box(Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        AllStamp("Tout les tampons")
    }
}