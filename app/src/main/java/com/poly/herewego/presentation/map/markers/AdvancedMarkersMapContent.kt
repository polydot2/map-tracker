package com.poly.herewego.presentation.map.markers

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.AdvancedMarkerOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.rememberMarkerState
import com.poly.herewego.R
import com.poly.herewego.data.mountains.model.Mountain
import com.poly.herewego.data.mountains.model.is14er
import com.poly.herewego.data.mountains.utils.toElevationString
import com.poly.herewego.ui.utils.BitmapParameters
import com.poly.herewego.ui.utils.vectorToBitmap

/**
 * [GoogleMapComposable] which renders a [MountainList] as a set of [AdvancedMarker]s
 */
@Composable
@GoogleMapComposable
fun AdvancedMarkersMapContent(
    mountains: List<Mountain>,
    onMountainClick: (Marker) -> Boolean = { false },
) {
    val mountainIcon = vectorToBitmap(
        LocalContext.current,
        BitmapParameters(
            id = R.drawable.baseline_filter_hdr_24,
            iconColor = MaterialTheme.colorScheme.onSecondary.toArgb(),
        )
    )

    val mountainPin = with(PinConfig.builder()) {
        setGlyph(PinConfig.Glyph(mountainIcon))
        setBackgroundColor(MaterialTheme.colorScheme.secondary.toArgb())
        setBorderColor(MaterialTheme.colorScheme.onSecondary.toArgb())
        build()
    }

    val fourteenerIcon = vectorToBitmap(
        LocalContext.current,
        BitmapParameters(
            id = R.drawable.baseline_filter_hdr_24,
            iconColor = MaterialTheme.colorScheme.onPrimary.toArgb(),
        )
    )

    val fourteenerPin = with(PinConfig.builder()) {
        setGlyph(PinConfig.Glyph(fourteenerIcon))
        setBackgroundColor(MaterialTheme.colorScheme.primary.toArgb())
        setBorderColor(MaterialTheme.colorScheme.onPrimary.toArgb())
        build()
    }

    mountains.forEach { mountain ->
        val pin = if (mountain.is14er()) fourteenerPin else mountainPin
        AdvancedMarker(
            state = rememberMarkerState(position = mountain.location),
            title = mountain.name,
            snippet = mountain.elevation.toElevationString(),
            collisionBehavior = AdvancedMarkerOptions.CollisionBehavior.REQUIRED_AND_HIDES_OPTIONAL,
            pinConfig = pin,
            onClick = { marker ->
                onMountainClick(marker)
                false
            }
        )
    }
}
