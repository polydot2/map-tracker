package com.poly.herewego.data.Map

import com.google.android.gms.maps.model.LatLng
import com.poly.herewego.data.Map.utils.Meters
import com.poly.herewego.data.Map.utils.feet

/**
 * Data class holding information about a mountain.
 */
data class Mountain(
    val id: Int,
    val name: String,
    val location: LatLng,
    val elevation: Meters,
)

/**
 * Extension function to determine whether a mountain is a "14er", i.e., has an elevation greater
 * than 14,000 feet (~4267 meters).
 */
fun Mountain.is14er() = elevation >= 14_000.feet