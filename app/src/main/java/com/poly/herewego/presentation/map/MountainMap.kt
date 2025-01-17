package com.poly.herewego.presentation.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.ScaleBar
import com.poly.herewego.R
import com.poly.herewego.data.Map.utils.DMS
import com.poly.herewego.data.Map.utils.Direction
import com.poly.herewego.data.Map.utils.toDecimalDegrees
import com.poly.herewego.presentation.map.markers.AdvancedMarkersMapContent
import com.poly.herewego.presentation.map.markers.BasicMarkersMapContent
import com.poly.herewego.presentation.map.markers.ClusteringMarkersMapContent
import com.poly.herewego.presentation.map.viewmodel.MountainsScreenEvent
import com.poly.herewego.presentation.map.viewmodel.MountainsScreenViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Shows a [GoogleMap] with collection of markers
 */
@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MountainMap(
    paddingValues: PaddingValues,
    viewState: MountainsScreenViewState.MountainList,
    eventFlow: Flow<MountainsScreenEvent>,
    selectedMarkerType: MarkerType,
) {
    var isMapLoaded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val mapId = stringResource(id = R.string.map_id)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewState.boundingBox.center, 5f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json)
            )
        )
    }

    LaunchedEffect(true) {
        eventFlow.collect { event ->
            when (event) {
                MountainsScreenEvent.OnZoomAll -> {
                    zoomAll(scope, cameraPositionState, viewState.boundingBox)
                }
            }
        }
    }

    LaunchedEffect(key1 = viewState.boundingBox) {
        zoomAll(scope, cameraPositionState, viewState.boundingBox)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            onMapLoaded = { isMapLoaded = true },
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId(mapId)
            }
        ) {
            ColoradoPolygon()

            when (selectedMarkerType) {
                MarkerType.Basic -> {
                    BasicMarkersMapContent(
                        mountains = viewState.mountains,
                    )
                }

                MarkerType.Advanced -> {
                    AdvancedMarkersMapContent(
                        mountains = viewState.mountains,
                    )
                }

                MarkerType.Clustered -> {
                    ClusteringMarkersMapContent(
                        mountains = viewState.mountains,
                        onClusterClick = { cluster ->
                            val newZoom = cameraPositionState.position.zoom + 1
                            scope.launch {
                                cameraPositionState.animate(
                                    update = CameraUpdateFactory.newLatLngZoom(
                                        cluster.position, newZoom
                                    ),
                                    durationMs = 500,
                                )
                            }
                            false
                        },
                    )
                }
            }
        }

        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )

        if (!isMapLoaded) {
            AnimatedVisibility(
                modifier = Modifier.matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Composable
@GoogleMapComposable
fun ColoradoPolygon() {
    // There are obvious advantages to drawing Colorado in this way...
    val north = 41.0
    val south = 37.0
    val east = DMS(Direction.WEST, 102.0, 3.0).toDecimalDegrees()
    val west = DMS(Direction.WEST, 109.0, 3.0).toDecimalDegrees()

    val locations = listOf(
        LatLng(north, east),
        LatLng(south, east),
        LatLng(south, west),
        LatLng(north, west),
    )

    Polygon(
        points = locations,
        strokeColor = MaterialTheme.colorScheme.tertiary,
        strokeWidth = 3F,
        fillColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
    )
}

fun zoomAll(
    scope: CoroutineScope,
    cameraPositionState: CameraPositionState,
    boundingBox: LatLngBounds
) {
    scope.launch {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngBounds(boundingBox, 64),
            durationMs = 1000
        )
    }
}

/**
 * An enumeration of the different marker types to demonstrate.
 */
enum class MarkerType(
    val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    Basic(
        title = R.string.basic_markers_label,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place,
    ),
    Advanced(
        title = R.string.advanced_markers_label,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place,

        ),
    Clustered(
        title = R.string.clustered_markers_label,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place,
    )
}
