package com.poly.herewego.ui.profile

import android.content.Intent
import android.content.IntentSender.OnFinished
import android.widget.ProgressBar
import android.widget.Space
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil3.compose.AsyncImage
import com.poly.herewego.R
import com.poly.herewego.utils.DrawableManager

@Composable
fun ProfileScreen(name: String, onOpenCategory: (category: String) -> Unit) {

    val data = listOf(
        Triple("Nantes", 0.5, false),
        Triple("Lyon", 0.2, false),
        Triple("Paris", 0.0, false),
        Triple("Rome", 0.18, false),
//        Pair("London", true),
//        Pair("Lille", false),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
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
        Box(Modifier.height(24.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(count = data.count()) { Badge(data[it].first, data[it].second, data[it].third, listener = { onOpenCategory(data[it].first) }) }
        }
    }
}

@Composable
fun Badge(name: String, percent: Double, check: Boolean, listener: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = listener)
            .padding(top = 12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(DrawableManager.getCategoryIcon(name)),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(name, maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
                Box(Modifier.size(48.dp), Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { percent.toFloat() },
                    )
                    Text((percent * 100).toString() + "%", maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
                }
                //            Icon(if (check) Icons.Filled.CheckCircle else Icons.Outlined.Info, contentDescription = "Check mark")
            }
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    ProfileScreen("name", {})
}
