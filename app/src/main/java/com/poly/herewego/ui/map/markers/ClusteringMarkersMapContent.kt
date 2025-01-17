package com.poly.herewego.ui.map.markers

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.poly.herewego.R
import com.poly.herewego.data.Map.Mountain
import com.poly.herewego.data.Map.is14er
import com.poly.herewego.data.Map.utils.LocalUnitsConverter
import com.poly.herewego.data.Map.utils.toElevationString

data class IconColor(val iconColor: Color, val backgroundColor: Color, val borderColor: Color)

data class MountainClusterItem(
    val mountain: Mountain,
    val snippetString: String
) : ClusterItem {
    override fun getPosition() = mountain.location
    override fun getTitle() = mountain.name
    override fun getSnippet() = snippetString
    override fun getZIndex() = 0f
}

/**
 * [GoogleMapComposable] which renders a [MountainList] using the [Clustering] composable
 */
@OptIn(MapsComposeExperimentalApi::class)
@Composable
@GoogleMapComposable
fun ClusteringMarkersMapContent(
    mountains: List<Mountain>,
    onClusterClick: (Cluster<out ClusterItem>) -> Boolean = { false },
    onMountainClick: (ClusterItem) -> Boolean = { false },
) {
    val unitsConverter = LocalUnitsConverter.current
    val resources = LocalContext.current.resources

    val backgroundAlpha = 0.6f

    val fourteenerColors = IconColor(
        iconColor = MaterialTheme.colorScheme.onPrimary,
        backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = backgroundAlpha),
        borderColor = MaterialTheme.colorScheme.primary
    )

    val otherColors = IconColor(
        iconColor = MaterialTheme.colorScheme.secondary,
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = backgroundAlpha),
        borderColor = MaterialTheme.colorScheme.secondary
    )

    val mountainClusterItems by remember(mountains) {
        mutableStateOf(
            mountains.map { mountain ->
                MountainClusterItem(
                    mountain = mountain,
                    snippetString = unitsConverter.toElevationString(resources, mountain.elevation)
                )
            }
        )
    }

    Clustering(
        items = mountainClusterItems,
        onClusterClick = { onClusterClick(it) },
        onClusterItemClick = {
            onMountainClick(it)
            false
        },
        onClusterItemInfoWindowClick = {
        },
        clusterContent = null,
        clusterItemContent = { mountainItem ->
            val colors = if (mountainItem.mountain.is14er()) {
                fourteenerColors
            } else {
                otherColors
            }
            SingleMountain(colors)
        },
    )
}

@Composable
private fun SingleMountain(
    colors: IconColor,
) {
    Icon(
        painterResource(id = R.drawable.baseline_filter_hdr_24),
        tint = colors.iconColor,
        contentDescription = "",
        modifier = Modifier
            .size(32.dp)
            .padding(1.dp)
            .drawBehind {
                drawCircle(color = colors.backgroundColor, style = Fill)
                drawCircle(color = colors.borderColor, style = Stroke(width = 3f))
            }
            .padding(4.dp)
    )
}
