package com.poly.herewego.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poly.herewego.ui.theme.AppTypography

@Composable
fun AnimatedBook() {
    // État pour contrôler l'angle de rotation
    var isOpen by remember { mutableStateOf(false) }

    // Animation de l'angle : de 0° (fermé) à 180° (ouvert)
    val rotationAngle by animateFloatAsState(
        targetValue = if (isOpen) 180f else 0f,
        animationSpec = tween(durationMillis = 1000) // Durée de l'animation : 1 seconde
    )

    Box(
        modifier = Modifier
            .size(110.dp) // Taille du livre
            .clickable { isOpen = !isOpen } // Clique pour déclencher l'animation
    ) {
        // Card de la tranche (en dessous, immobile)
        Card(
            modifier = Modifier
                .size(110.dp)
                .offset(x = 10.dp), // Décalage pour donner l'impression de tranche
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            ),

            ) {
            // Contenu de la tranche (vide ou personnalisé)
        }

        // Card de la couverture (au-dessus, animée)
        Card(
            modifier = Modifier
                .size(110.dp)
                .graphicsLayer {
                    // Déplacer l'élément pour que le bord droit soit à l'origine
                    translationX = -size.width / 2f // Déplace vers la gauche de width/2
                    // Appliquer la rotation autour de l'axe Y
                    rotationY = -rotationAngle
                    // Ajuster la perspective
                    cameraDistance = 8f * density
                    // Corriger l'effet miroir pour le contenu
                    if (rotationAngle < -90f) {
                        rotationY -= 180f // Ajustement pour rotation négative
                    }
                    // Repositionner après la rotation pour maintenir l'alignement
                    translationX += size.width / 2f * kotlin.math.cos(Math.toRadians(rotationAngle.toDouble())).toFloat()
                },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2196F3)
            ),
        ) {
            Box(Modifier.padding(top = 4.dp, start = 4.dp)) {
                Icon(
                    Icons.Rounded.Place, modifier = Modifier
                        .align(Alignment.Center), tint = Color.Black.copy(alpha = 0.2f), contentDescription = "icon passport"
                )
            }
            Column(
                Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 6.dp)
                    .fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                    )
                    Text(
                        text = "iconText",
                        modifier = Modifier.align(Alignment.Center),
                        style = AppTypography.headlineLarge
                    )
                }
                Text("data", fontWeight = FontWeight.Bold)
                Icon(Icons.Rounded.Menu, contentDescription = "icon star")
            }
        }
    }
}