package com.poly.herewego.data

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val id: String,
    val creationDate: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val lat: Float,
    val lng: Float,
)