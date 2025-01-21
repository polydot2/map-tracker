package com.poly.herewego.presentation.map.markers

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.poly.herewego.R
import com.poly.herewego.data.mountains.model.Mountain
import com.poly.herewego.data.mountains.model.is14er
import com.poly.herewego.data.mountains.utils.toElevationString
import com.poly.herewego.ui.utils.BitmapParameters
import com.poly.herewego.ui.utils.vectorToBitmap

/**
 * [GoogleMapComposable] which renders a [MountainList] as a set of basic [Marker]s
 */
@Composable
@GoogleMapComposable
fun BasicMarkersMapContent(
    mountains: List<Mountain>,
    onMountainClick: (Marker) -> Boolean = { false }
) {
    // Create mountainIcon and fourteenerIcon
    val mountainIcon = vectorToBitmap(
        LocalContext.current,
        BitmapParameters(
            id = R.drawable.baseline_filter_hdr_24,
            iconColor = MaterialTheme.colorScheme.secondary.toArgb(),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer.toArgb(),
        )
    )

    val fourteenerIcon = vectorToBitmap(
        LocalContext.current,
        BitmapParameters(
            id = R.drawable.baseline_filter_hdr_24,
            iconColor = MaterialTheme.colorScheme.onPrimary.toArgb(),
            backgroundColor = MaterialTheme.colorScheme.primary.toArgb(),
        )
    )

    mountains.forEach { mountain ->
        val icon = if (mountain.is14er()) fourteenerIcon else mountainIcon
        Marker(
            state = rememberMarkerState(position = mountain.location),
            title = mountain.name,
            snippet = mountain.elevation.toElevationString(),
            tag = mountain,
            anchor = Offset(0.5f, 0.5f),
            icon = icon,
            onClick = { marker ->
                onMountainClick(marker)
                false
            },
            zIndex = if (mountain.is14er()) 5f else 2f
        )
    }
}
