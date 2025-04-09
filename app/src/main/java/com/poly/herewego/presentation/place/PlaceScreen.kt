package com.poly.herewego.presentation.place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.poly.herewego.R
import com.poly.herewego.ui.theme.AppTheme

@Composable
fun PlaceScreen(id: String, alreadyVisited: Boolean, onBackPressed: () -> Unit = {}) {
    var playAnimation by remember { mutableStateOf(false) }
    var isVisited by remember { mutableStateOf(alreadyVisited) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.check_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = playAnimation,
        iterations = 1
    )

    Column(Modifier.padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)) {
        Row(Modifier.padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackPressed, modifier = Modifier
                    .background(color = Color.White, shape = CircleShape)
                    .size(32.dp)
            ) {
                Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = "icon")
            }
            Box(Modifier.width(12.dp))
            Text("Le Lieu unique", style = MaterialTheme.typography.headlineLarge)
        }
        Card {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentScale = ContentScale.FillWidth,
                model = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Nantes_lieu_unique_tour.JPG/390px-Nantes_lieu_unique_tour.JPG",
                contentDescription = "Translated description of what the image contains"

            )
            Column(Modifier.padding(12.dp)) {
                Text("Le saviez vous ?", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 12.dp))
                Text("Le Lieu unique est un centre de culture contemporaine labellisé scène nationale, créé à Nantes le 1er janvier 2000 et installé dans les anciens locaux de la biscuiterie LU, dont les initiales sont aussi celles du centre.")
            }
        }
        Box(Modifier.height(12.dp))

        if (!isVisited) {
            Card {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text("Vous n'avez pas encore validé ce lieu !", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 12.dp))
                    Text("Soumettez une photo souvenir pour valider le lieu et obtenir votre badge certifié !", fontStyle = FontStyle.Italic)
                    Box(Modifier.height(12.dp))
                    Row(Modifier.align(Alignment.Start)) {
                        Button(onClick = {
                            playAnimation = true
                        }) { Text("Obtenez votre tampon !") }
                    }
                }
            }
        } else {
            Card {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Column {
                            Row {
                                Text("Vous avez déjà visité ce lieu", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 12.dp))
                            }

                            Text("Le 12/03/2004, bravo :)", fontStyle = FontStyle.Italic)
                            Box(Modifier.height(12.dp))
                        }
                        Icon(
                            Icons.Rounded.CheckCircle,
                            modifier = Modifier.size(72.dp).aspectRatio(1f),
                            contentDescription = "check icon",
                            tint = Color(0xFF3DC482),
                        )
                    }
                }
            }
        }
    }

    if (playAnimation) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(300.dp),
                iterations = 1
            )

            if (progress >= 1f) {
                playAnimation = false
                isVisited = true
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        PlaceScreen("Tour LU", alreadyVisited = false)
    }
}

@Preview
@Composable
fun DefaultPreviewIsVisited() {
    AppTheme {
        PlaceScreen("Tour LU", alreadyVisited = true)
    }
}
