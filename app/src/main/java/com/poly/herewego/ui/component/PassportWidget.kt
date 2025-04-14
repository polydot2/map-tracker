package com.poly.herewego.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poly.herewego.data.Passport
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.theme.AppTypography

fun hexStringToLong(hex: String): Long {
    return hex.replace("0x", "", ignoreCase = true).toLong(16)
}

@Composable
fun PassportWidget(
    data: Passport?,
    title: String,
    colorString: String,
    iconText: String,
    onCategoryClick: (data: Passport) -> Unit,
    animation: Boolean = false
) {
    // État pour contrôler l'angle de rotation
    var isOpen by remember { mutableStateOf(false) }

    // Animation de l'angle : de 0° (fermé) à 180° (ouvert)
    val rotationAngle by animateFloatAsState(
        targetValue = if (isOpen) 180f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    val color = hexStringToLong(colorString)

    // Définir colorTitle en fonction de rotationAngle dans la composition
    val colorTitle = if (rotationAngle > 90f) Color(color) else Color.Black

    Box(
        Modifier
            .width(110.dp)
            .height(146.dp)
    ) {
        Card(
            onClick = { },
            Modifier
                .width(110.dp)
                .height(146.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE1E1E1)
            ),
        ) {
            Column(
                Modifier
                    .padding(6.dp)
                    .fillMaxHeight(),
                Arrangement.SpaceBetween
            ) {
                Text("Voir la map")
            }
        }

        Card(
            onClick = { data?.let { if (!animation) onCategoryClick(it) else isOpen = !isOpen } },
            Modifier
                .width(104.dp)
                .height(146.dp)
                .graphicsLayer {
                    clip = false
                    // Déplacer l'élément pour que le bord droit soit à l'origine
                    translationX = -size.width / 2f
                    // Appliquer la rotation autour de l'axe Y
                    rotationY = -rotationAngle
                    // Ajuster la perspective
                    cameraDistance = 8f * density
                    // Corriger l'effet miroir pour le contenu
                    if (rotationAngle < -90f) {
                        rotationY -= 180f
                    }
                    // Repositionner après la rotation pour maintenir l'alignement
                    translationX += size.width / 2f * kotlin.math.cos(
                        Math.toRadians(rotationAngle.toDouble())
                    ).toFloat()
                },
            colors = CardDefaults.cardColors(
                containerColor = Color(color)
            ),
        ) {
            Box(Modifier.padding(top = 4.dp, start = 4.dp)) {
                Icon(
                    Icons.Rounded.Place,
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.Black.copy(alpha = 0.2f),
                    contentDescription = "icon passport"
                )
            }
            Column(
                Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 6.dp)
                    .fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                    Text(
                        text = iconText,
                        modifier = Modifier.align(Alignment.Center),
                        style = AppTypography.headlineLarge
                    )
                }
                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    color = colorTitle
                )
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = "icon star",
                    tint = colorTitle
                )
            }
        }
    }
}

@Preview
@Composable
fun PassportPreview() {
    AppTheme {
        PassportWidget(null, "Nantes", "0xFF00BCD4", "\uD83D\uDC18", {}, true)
    }
}

