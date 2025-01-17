package com.poly.herewego.data.discovery

import com.poly.herewego.data.discovery.api.DiscoveryWebService
import com.poly.herewego.data.discovery.model.PlaceResponse

class DiscoverRepository(private val webService: DiscoveryWebService = DiscoveryWebService()) {
    suspend fun getCities(): List<PlaceResponse> {
        return webService.getCities()
    }
}