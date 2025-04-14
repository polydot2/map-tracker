package com.poly.herewego.data

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Passport(
    val id: String,
    val creationDate: String,
    val name: String,
    val color: String,
    val icon: String,
    val lat: Float,
    val lng: Float,
    var places: List<Place>,
)

