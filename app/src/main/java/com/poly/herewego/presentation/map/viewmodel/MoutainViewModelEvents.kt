package com.poly.herewego.presentation.map.viewmodel

/**
 * Events that the UI can send to the [MountainsViewModel]
 */
sealed class MountainsViewModelEvent {
    data object OnZoomAll: MountainsViewModelEvent()
    data object OnToggleAllPeaks: MountainsViewModelEvent()
}