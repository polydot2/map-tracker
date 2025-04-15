package com.poly.herewego.data.discovery.model

import com.google.android.gms.maps.model.LatLng

data class PlaceDto(
    var id: String,
    var name: String,
    var country: String,
    var location: LatLng,
    var pop: String,
)

fun PlaceResponse.toEntity(): PlaceDto {
    return PlaceDto(
        id = this.id,
        name = this.name,
        country = this.country,
        location = LatLng(this.lat.toDouble(), this.lon.toDouble()),
        pop = this.pop,
    )
}
