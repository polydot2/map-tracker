package com.poly.herewego.data

import java.time.LocalDate

data class Passport(
    val id: String,
    val creationDate: LocalDate,
    val name: String,
    val color: String,
    val icon: String,
    val lat: Float,
    val lng: Float,
    var places: List<Place>,
)

