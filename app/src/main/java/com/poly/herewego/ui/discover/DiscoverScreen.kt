package com.poly.herewego.ui.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poly.herewego.data.discovery.model.PlaceResponse

@Composable
fun DiscoverScreen() {
    val viewModel: DiscoverViewModel = viewModel()
    val cities = viewModel.placeState.value

    Column(modifier = Modifier.padding(12.dp)) {
        Text("Discovery", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))
        if (cities.isEmpty())
//        Button(onClick = { viewModel.fetch() }) { }
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
            )
        else
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                items(items = cities, itemContent = { PlaceItem(it) })
            }
    }
}

@Composable
fun PlaceItem(meal: PlaceResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
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
                )
                Text(
                    text = meal.country,
                )
                Text(
                    text = meal.admin1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiscoverScreen()
}