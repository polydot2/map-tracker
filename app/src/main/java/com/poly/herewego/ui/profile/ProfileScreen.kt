package com.poly.herewego.ui.profile

import android.content.Intent
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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.R
import com.poly.herewego.ui.place.PlaceScreen
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProfileScreen(name: String) {

    val data = listOf(
        Pair("Nantes 50%", false),
//        Pair("Lyon", false),
//        Pair("Paris", false),
//        Pair("Rome", false),
//        Pair("London", true),
//        Pair("Lille", false),
    )

    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
//            Image(
//                painter = painterResource(R.drawable.ic_launcher_background),
//                contentDescription = "avatar",
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//            )
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
                Text("Mini marcheur \uD83D\uDD7A")
            }
        }
        Box(Modifier.height(12.dp))
        Box(Modifier.height(12.dp))
        LazyVerticalGrid(
            contentPadding = PaddingValues(12.dp),
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(count = data.count()) { Badge(data[it].first, data[it].second, listener = { startDetailActivity(data[it].first) }) }
        }
    }
}

fun startDetailActivity(category: String) {
//    val context = LocalContext.current
//    val intent = Intent(context, PlaceScreen::class.java)
//    context.startActivity(intent)

}

@Composable
fun Badge(name: String, check: Boolean, listener: () -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = listener)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Filled.Home, contentDescription = name + "icon")
            Text(name, maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
//            Icon(if (check) Icons.Filled.CheckCircle else Icons.Outlined.Info, contentDescription = "Check mark")
            Text("\uD83E\uDE99")
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    ProfileScreen("name")
}
