package com.poly.herewego.ui.map


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poly.herewego.data.Map.MountainsRepository
import com.poly.herewego.ui.map.viewmodel.MountainsScreenViewState
import com.poly.herewego.ui.map.viewmodel.MountainsViewModel

@Composable
fun MapScreen() {
    val viewModel: MountainsViewModel = MountainsViewModel(MountainsRepository(context = LocalContext.current))
    val screenViewState = viewModel.mountainsScreenViewState.collectAsState()
    val viewState = screenViewState.value

    Column(
        Modifier
            .padding(12.dp)
    ) {
        Text("Map", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
    }

    when (viewState) {
        MountainsScreenViewState.Loading -> Text("loading")
        is MountainsScreenViewState.MountainList -> {
            MountainMap(
                PaddingValues(0.dp),
                viewState,
                viewModel.getEventChannel(),
                MarkerType.Basic
            )
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    MapScreen()
}