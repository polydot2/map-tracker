package com.poly.herewego.presentation.category

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.ui.utils.DrawableManager
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(category: String, onOpenDetails: (place: String) -> Unit) {
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

            Row(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(DrawableManager.getCategoryIcon("nantes")),
                    contentDescription = "nantes icon",
                    modifier = Modifier
                        .clip(CircleShape)
                )
                Box(Modifier.width(12.dp))
                Text("Nantes", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Column(Modifier.padding(12.dp)) {
            Text("Liste des points d'interêts")
            Box(Modifier.height(12.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = list, itemContent = { PlaceItem(it.first, it.second, onOpenDetails) })
            }
        }

//        SimpleBottomSheetScaffoldSample()
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
    CategoryScreen("Nantes", {})
}
