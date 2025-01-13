package com.poly.herewego.ui.map


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
import com.poly.herewego.ui.discover.DiscoverViewModel
import com.poly.herewego.ui.discover.PlaceItem

@Composable
fun MapScreen() {
    val viewModel: DiscoverViewModel = viewModel()
    val cities = viewModel.placeState.value

    Column(modifier = Modifier.padding(12.dp)) {
        androidx.compose.material3.Text("Map", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
    }
}