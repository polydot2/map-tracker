package com.poly.herewego.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poly.herewego.ui.theme.AppTypography


@Composable
fun PassportWidget(data: String, color: Long, iconText: String, onCategoryClick: (data: String) -> Unit) {
    Card(
        onClick = { onCategoryClick(data) },
        Modifier.width(110.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE1E1E1)
        ),
    ) {
        Card(
            Modifier.padding(end = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(color)
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
                        text = iconText,
                        modifier = Modifier.align(Alignment.Center),
                        style = AppTypography.headlineLarge
                    )
                }
                Text(data, fontWeight = FontWeight.Bold)
                Icon(Icons.Rounded.Menu, contentDescription = "icon star")
            }
        }
    }
}
