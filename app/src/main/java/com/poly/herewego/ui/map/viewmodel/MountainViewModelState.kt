package com.poly.herewego.ui.map.viewmodel

import com.google.android.gms.maps.model.LatLngBounds
import com.poly.herewego.data.Map.Mountain

/**
 * Sealed class representing the state of the mountain map view.
 */
sealed class MountainsScreenViewState {
    data object Loading : MountainsScreenViewState()
    data class MountainList(
        // List of the mountains to display
        val mountains: List<Mountain>,

        // Bounding box that contains all of the mountains
        val boundingBox: LatLngBounds,

        // Switch indicating whether all the mountains or just the 14ers
        val showingAllPeaks: Boolean = false,
    ) : MountainsScreenViewState()
}