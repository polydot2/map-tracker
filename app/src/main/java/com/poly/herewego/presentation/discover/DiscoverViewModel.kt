package com.poly.herewego.presentation.discover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLngBounds
import com.poly.herewego.data.discovery.DiscoverRepository
import com.poly.herewego.data.discovery.model.PlaceEntity
import com.poly.herewego.data.discovery.model.PlaceResponse
import com.poly.herewego.data.mountains.model.Mountain
import com.poly.herewego.data.mountains.model.is14er
import com.poly.herewego.presentation.map.viewmodel.MountainsScreenEvent
import com.poly.herewego.presentation.map.viewmodel.MountainsScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import toLatLngBounds
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val repository: DiscoverRepository) : ViewModel() {
    // Whether or not to show all of the high peaks
    private var showAllPlaces = MutableStateFlow(true)

    val placesScreenViewState =
        repository.places.combine(showAllPlaces) { allPlaces, showAllPlaces ->
            if (allPlaces.isEmpty()) {
                MountainsScreenViewState.Loading
            } else {
                val filteredMountains = if (showAllPlaces) allPlaces else allPlaces.take(4)
                val boundingBox = filteredMountains.map { it.location }.toLatLngBounds()
                ScreenViewState.PlacesList(
                    mountains = filteredMountains,
                    boundingBox = boundingBox,
                    showingAllPeaks = showAllPlaces,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MountainsScreenViewState.Loading
        )

    init {
        viewModelScope.launch {
            repository.loadPlaces()
        }
    }
}


/**
 * Sealed class representing the state of the mountain map view.
 */
sealed class ScreenViewState {
    data object Loading : ScreenViewState()
    data class PlacesList(
        // List of the mountains to display
        val mountains: List<PlaceEntity>,

        // Bounding box that contains all of the mountains
        val boundingBox: LatLngBounds,

        // Switch indicating whether all the mountains or just the 14ers
        val showingAllPeaks: Boolean = false,
    ) : ScreenViewState()
}