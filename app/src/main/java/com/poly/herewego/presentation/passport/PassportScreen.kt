package com.poly.herewego.presentation.passport

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.poly.herewego.ui.component.PassportWidget
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.theme.AppTypography
import com.poly.herewego.ui.utils.DrawableManager
import com.poly.herewego.ui.utils.dashedBorder

@Composable
fun PassportScreen() {
    var list = listOf(Pair("Le Lieu unique", false), Pair("Chateau des ducs", true), Pair("Elephant \uD83D\uDC18", false), Pair("Jardin des plantes", true))

    Column() {
        Box(Modifier.background(Color.Black)) {
            AsyncImage(
                modifier = Modifier
                    .height(256.dp)
                    .fillMaxWidth()
                    .alpha(0.5f),
                model = "https://www.exponantes.com/system/galery_attachments/133/attachments/original_nantes-1131.jpg",
                contentScale = ContentScale.FillWidth,
                contentDescription = "Nantes town illustration"
            )

            Row(
                Modifier.padding(top = 32.dp, start = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { }, modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .size(32.dp)
                ) {
                    Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = "icon")
                }
                Box(Modifier.width(12.dp))
            }

//            Box(Modifier
//                .align(Alignment.TopEnd)
//                .padding(top = 32.dp, end = 12.dp)) {
//                PassportWidget("Nantes", 0xFF00BCD4, "\uD83D\uDC18", {})
//            }
        }

        Box(
            Modifier
                .padding(start = 12.dp)
                .offset(y = -128.dp)
        ) {
            PassportWidget("Nantes", 0xFF00BCD4, "\uD83D\uDC18", {})
        }

        Box(
            Modifier
                .offset(y = -178.dp)
                .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                .background(Color.White)
        ) {
            Column(Modifier.padding(12.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text("Nantes", style = AppTypography.headlineLarge)
                        Text("5 / 14 sites visités")
                    }
                    Card(
                        shape = CircleShape, modifier = Modifier
                            .size(128.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(), // Remplit la Card
                            contentAlignment = Alignment.Center // Centre le contenu

                        ) {
                            Text("C'est la carte !")
                        }
                    }
                }
                Box(Modifier.height(12.dp))
                PoiWidget(list)
                Box(Modifier.height(12.dp))
                HistoryWidget()
            }
        }
    }
}

@Composable
fun HistoryWidget() {
    Column {
        Text("Historique", style = AppTypography.headlineMedium)
        Text("Check elephant le 12/04/2025")
        Text("Check elephant le 15/05/2025")
    }
}

@Composable
fun PoiWidget(list: List<Pair<String, Boolean>>) {
    Text("Liste des points d'interêts", style = AppTypography.headlineMedium)
    Box(Modifier.height(12.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(list.size) { index ->
                PlaceItem(list[index].first, list[index].second, { })
            }
        }
    )
}

@Composable
fun PlaceItem(name: String, checked: Boolean, onClick: (place: String) -> Unit) {
    Card(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClick(name) })
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Text(name, modifier = Modifier.padding(12.dp))
            Checkbox(checked = checked, enabled = false, onCheckedChange = {})
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        PassportScreen()
    }
}

