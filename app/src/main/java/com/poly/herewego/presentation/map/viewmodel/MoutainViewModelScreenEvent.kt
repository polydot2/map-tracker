package com.poly.herewego.presentation.map.viewmodel

/**
 * Events that can be sent to the MapScreen
 */
sealed class MountainsScreenEvent {
    data object OnZoomAll: MountainsScreenEvent()
}