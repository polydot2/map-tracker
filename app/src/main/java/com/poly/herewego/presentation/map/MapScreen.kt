package com.poly.herewego.presentation.map


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poly.herewego.presentation.map.viewmodel.MountainsScreenViewState
import com.poly.herewego.presentation.map.viewmodel.MountainsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.poly.herewego.ui.theme.AppTheme

@Composable
fun MapScreen() {
    val viewModel: MountainsViewModel = hiltViewModel()
    val screenViewState = viewModel.mountainsScreenViewState.collectAsState()
    val viewState = screenViewState.value

    Column(
        Modifier.padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Text("Map", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
    }

    when (viewState) {
        MountainsScreenViewState.Loading -> Text("loading")
        is MountainsScreenViewState.MountainList -> {
            Text(viewState.mountains.toString())
            MountainMap(
                PaddingValues(0.dp),
                viewState,
                viewModel.getEventChannel(),
                MarkerType.Basic
            )
//            MapboxMap(
//                Modifier.fillMaxSize(),
//                mapViewportState = rememberMapViewportState {
//                    setCameraOptions {
//                        zoom(2.0)
//                        center(Point.fromLngLat(-98.0, 39.5))
//                        pitch(0.0)
//                        bearing(0.0)
//                    }
//                })
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        MapScreen()
    }
}