package com.poly.herewego.ui.map


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poly.herewego.ui.discover.DiscoverViewModel
import com.poly.herewego.ui.place.PlaceScreen

@Composable
fun MapScreen() {
    val viewModel: DiscoverViewModel = viewModel()
    val cities = viewModel.placeState.value

    Column(
        Modifier
            .padding(12.dp)
    ) {
    androidx.compose.material3.Text("Map", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    PlaceScreen("Tour LU")
}
