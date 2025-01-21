package com.poly.herewego.presentation.map.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poly.herewego.data.mountains.MountainsRepository
import com.poly.herewego.data.mountains.model.is14er
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import toLatLngBounds
import javax.inject.Inject

/**
 * ViewModel for loading and managing the list of mountains
 */
@HiltViewModel
class MountainsViewModel @Inject constructor(
    mountainsRepository: MountainsRepository
) : ViewModel() {

    private val _eventChannel = Channel<MountainsScreenEvent>()

    // Event channel to send events to the UI
    internal fun getEventChannel() = _eventChannel.receiveAsFlow()

    // Whether or not to show all of the high peaks
    private var showAllMountains = MutableStateFlow(false)

    val mountainsScreenViewState =
        mountainsRepository.mountains.combine(showAllMountains) { allMountains, showAllMountains ->
            if (allMountains.isEmpty()) {
                MountainsScreenViewState.Loading
            } else {
                val filteredMountains =
                    if (showAllMountains) allMountains else allMountains.filter { it.is14er() }
                val boundingBox = filteredMountains.map { it.location }.toLatLngBounds()
                MountainsScreenViewState.MountainList(
                    mountains = filteredMountains,
                    boundingBox = boundingBox,
                    showingAllPeaks = showAllMountains,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MountainsScreenViewState.Loading
        )

    init {
        // Load the full set of mountains
        viewModelScope.launch {
            mountainsRepository.loadMountains()
        }
    }

    // Handle user events
    fun onEvent(event: MountainsViewModelEvent) {
        when (event) {
            MountainsViewModelEvent.OnZoomAll -> onZoomAll()
            MountainsViewModelEvent.OnToggleAllPeaks -> toggleAllPeaks()
        }
    }

    private fun onZoomAll() {
        sendScreenEvent(MountainsScreenEvent.OnZoomAll)
    }

    private fun toggleAllPeaks() {
        showAllMountains.value = !showAllMountains.value
    }

    // Send events back to the UI via the event channel
    private fun sendScreenEvent(event: MountainsScreenEvent) {
        viewModelScope.launch { _eventChannel.send(event) }
    }
}
