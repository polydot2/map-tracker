package com.poly.herewego.presentation.place

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun PlaceScreen(id: String) {
    Column(Modifier.padding(12.dp)) {
        Text("Le Lieu unique", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))
        Card() {
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

        Card() {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text("Vous n'avez pas encore validé ce lieu !", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 12.dp))
                Text("Soumettez une photo souvenir pour valider le lieu et obtenir votre badge certifié !", fontStyle = FontStyle.Italic)
                Box(Modifier.height(12.dp))
                Row(Modifier.align(Alignment.Start)) {
                    Button(onClick = {}) { Text("Obtenez votre tampon !") }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    PlaceScreen("Tour LU")
}
