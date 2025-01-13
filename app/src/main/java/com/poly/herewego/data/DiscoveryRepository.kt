package com.poly.herewego.data

import com.poly.herewego.data.api.DiscoveryWebService
import com.poly.herewego.data.model.PlaceResponse

class DiscoverRepository(private val webService: DiscoveryWebService = DiscoveryWebService()) {
    suspend fun getCities(): List<PlaceResponse> {
        return webService.getCities()
    }
}