package com.poly.herewego.presentation.discover

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poly.herewego.data.discovery.model.PlaceDto

@Composable
fun DiscoverScreen() {
    val viewModel: DiscoveryViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(12.dp)) {
        Text("Discovery", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))

        when (uiState) {
            is DiscoveryUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                )
            }

            is DiscoveryUiState.Success -> {
                var data = (uiState as DiscoveryUiState.Success).data
                LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(items = data, itemContent = { PlaceItem(it) })
                }
            }

            is DiscoveryUiState.Error -> {
                var data = (uiState as DiscoveryUiState.Error).message
                Text(text = data)
            }
        }
    }
}

@Composable
fun PlaceItem(meal: PlaceDto) {
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiscoverScreen()
}