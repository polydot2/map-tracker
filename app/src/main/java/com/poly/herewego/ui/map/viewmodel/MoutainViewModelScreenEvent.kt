package com.poly.herewego.ui.map.viewmodel

/**
 * Events that can be sent to the MapScreen
 */
sealed class MountainsScreenEvent {
    data object OnZoomAll: MountainsScreenEvent()
}