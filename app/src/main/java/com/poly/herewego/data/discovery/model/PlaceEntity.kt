package com.poly.herewego.data.discovery.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class PlaceEntity(
    var id: String,
    var name: String,
    var country: String,
    var admin1: String,
    var location: LatLng,
    var pop: String,
) {
    companion object {
        fun from(it: PlaceResponse): PlaceEntity {
            return PlaceEntity(
                id = it.id,
                name = it.name,
                country = it.country,
                admin1 = it.admin1,
                location = LatLng(it.lat.toDouble(), it.lon.toDouble()),
                pop = it.pop,
            )
        }
    }
}