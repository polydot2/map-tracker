package com.poly.herewego.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.poly.herewego.R
import com.poly.herewego.ui.theme.AppTypography

@Composable
fun MyProfile(title: String, subtitle: String, onProfileClick: () -> Unit) {
//    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold)
//    Box(Modifier.height(12.dp))
    Card (onClick = onProfileClick) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Box(Modifier.width(12.dp))
            Column {
                Text("Mini explorer", style = AppTypography.headlineMedium)
                Text(subtitle)
            }
        }
    }
    Box(Modifier.height(24.dp))
}