package com.poly.herewego.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.utils.DrawableManager
import com.poly.herewego.ui.utils.dashedBorder

@Composable
fun ProfileScreen(name: String, onOpenCategory: (category: String) -> Unit, onAddTour: () -> Unit) {

    val data = listOf(
        Triple("Voyage à Nantes 2025 - 2/4", 0.5, false),
//        Triple("Lyon", 0.2, false),
//        Triple("Paris", 0.0, false),
//        Triple("Rome", 0.18, false),
//        Pair("London", true),
//        Pair("Lille", false),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))
        Box(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                model = "https://i.pravatar.cc/150?img=3",
                contentDescription = "Translated description of what the image contains"
            )
            Box(Modifier.width(12.dp))
            Column {
                Text("Luke Bigwalker", style = MaterialTheme.typography.headlineMedium)
                Text("Mini explorer \uD83D\uDD7A")
            }
        }
//        Box(Modifier.height(24.dp))
//        LazyVerticalGrid(
//            columns = GridCells.Adaptive(minSize = 128.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(count = data.count()) { Badge(data[it].first, data[it].second, data[it].third, listener = { onOpenCategory(data[it].first) }) }
//        }
        Box(Modifier.height(24.dp))
        Column() {
            Text("Mes voyages", style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.height(12.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(count = data.count()) { Badge(data[it].first, data[it].second, data[it].third, listener = { onOpenCategory(data[it].first) }) }
            }
            Box(Modifier.height(12.dp))
            BadgeAdd(onAddTour)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun Badge(name: String, percent: Double, check: Boolean, listener: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = listener)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(DrawableManager.getCategoryIcon(name)),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Box(Modifier.width(12.dp))
            Column {
                Text(name.uppercase(), maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
                LinearProgressIndicator(progress = { percent.toFloat() }, modifier = Modifier.height(8.dp), strokeCap = StrokeCap.Round)
            }
//                Box(Modifier.align(Alignment.Bottom)) {
//                    Text(String.format("%.0f", percent * 100) + "%", maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
//                }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = "icon")
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BadgeAdd(listener: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .dashedBorder(
                color = MaterialTheme.colorScheme.surfaceTint,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = listener)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Add, contentDescription = "icon",
                modifier = Modifier
                    .clip(CircleShape)
            )
            Box(Modifier.width(12.dp))
            Column {
                Text("Add a tour", maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.weight(1f))
//            Icon(Icons.Rounded.FavoriteBorder, contentDescription = "icon")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        ProfileScreen("name", {}, {})
    }
}

