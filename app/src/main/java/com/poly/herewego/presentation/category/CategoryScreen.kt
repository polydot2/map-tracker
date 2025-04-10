package com.poly.herewego.presentation.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.presentation.discover.PlaceItem

@Composable
fun CategoryScreen(category: String, onOpenDetails: (place: String) -> Unit = {}, onBackPressed: () -> Unit = {}) {
    var list = listOf(Pair("Le Lieu unique", false), Pair("Chateau des ducs", true), Pair("Elephant \uD83D\uDC18", false), Pair("Jardin des plantes", true))

    Column {
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
            Row(Modifier.padding(top = 32.dp, start = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onBackPressed, modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .size(32.dp)
                ) {
                    Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = "icon")
                }
                Box(Modifier.width(12.dp))
                Text("Nantes", style = MaterialTheme.typography.headlineLarge, color = Color.White)
            }
//            Row(
//                Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(12.dp)
//            ) {
//                Image(
//                    painter = painterResource(DrawableManager.getCategoryIcon("nantes")),
//                    contentDescription = "nantes icon",
//                    modifier = Modifier
//                        .clip(CircleShape)
//                )
//                Box(Modifier.width(12.dp))
//                Text("Nantes", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Color.White)
//            }
        }

        Column(Modifier.padding(12.dp)) {
            Text("Liste des points d'interêts")
            Box(Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                content = {
                    items(list.size) { index ->
                        PlaceItem(list[index].first, list[index].second, onOpenDetails)
                    }
                }
            )
//        SimpleBottomSheetScaffoldSample()
        }
    }
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

fun onOpenSheet() {
}
//
//@Preview
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SimpleBottomSheetScaffoldSample() {
//    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberBottomSheetScaffoldState()
//
//    BottomSheetScaffold(
//        scaffoldState = scaffoldState,
//        sheetContent = {
//            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(128.dp), contentAlignment = Alignment.Center) {
//                    Text("Swipe up to expand sheet")
//                }
//                Text("Sheet content")
//                Button(
//                    modifier = Modifier.padding(bottom = 64.dp),
//                    onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } }
//                ) {
//                    Text("Click to collapse sheet")
//                }
//            }
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("Scaffold Content")
//        }
//    }
//}

@Preview
@Composable
fun DefaultPreview() {
    CategoryScreen("Nantes", {}, {})
}
