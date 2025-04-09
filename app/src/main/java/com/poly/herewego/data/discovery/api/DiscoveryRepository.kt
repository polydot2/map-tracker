package com.poly.herewego.data.discovery.api

import com.poly.herewego.data.discovery.model.PlaceEntity

interface DiscoveryRepository {
    suspend fun getCities(): Result<List<PlaceEntity>>
}