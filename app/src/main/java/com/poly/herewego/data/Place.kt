package com.poly.herewego.data

import java.time.LocalDate

data class Place(
    val id: String,
    val creationDate: LocalDate,
    val name: String,
    val description: String,
    val imageUrl: String,
    val lat: Float,
    val lng: Float,
)