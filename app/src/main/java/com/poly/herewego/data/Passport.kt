package com.poly.herewego.data

import kotlinx.serialization.Serializable

@Serializable
data class Passport(
    val id: String,
    val creationDate: String,
    val name: String,
    val color: String,
    val icon: String,
    val lat: Float,
    val lng: Float,
    val imageUrl: String,
    var places: List<Place>,
)

