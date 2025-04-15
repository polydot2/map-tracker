package com.poly.herewego.data.discovery.api

import com.poly.herewego.data.discovery.model.PlaceDto

interface DiscoveryRepository {
    suspend fun getCities(): Result<List<PlaceDto>>
}