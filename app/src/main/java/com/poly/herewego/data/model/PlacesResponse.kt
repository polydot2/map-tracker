package com.poly.herewego.data.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("admin1") val admin1: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String,
    @SerializedName("pop") val pop: String,
)
