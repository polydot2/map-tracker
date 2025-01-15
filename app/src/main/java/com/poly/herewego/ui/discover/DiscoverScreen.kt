package com.poly.herewego.ui.discover

import android.widget.ProgressBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poly.herewego.data.model.PlaceResponse

@Composable
fun DiscoverScreen() {
    val viewModel: DiscoverViewModel = viewModel()
    val cities = viewModel.placeState.value

    Column(modifier = Modifier.padding(12.dp)) {
        androidx.compose.material3.Text("Discovery", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
    }
    if (cities.isEmpty())
//        Button(onClick = { viewModel.fetch() }) { }
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
        )
    else
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(items = cities, itemContent = { PlaceItem(it) })
        }
}

@Composable
fun PlaceItem(meal: PlaceResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
//            Image(
//                painter = rememberImagePainter(meal.imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(88.dp)
//                    .padding(4.dp)
//            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = meal.country,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = meal.admin1,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MealzAppTheme {
//        MealsCategoriesScreen()
//    }
//}