package com.poly.herewego.domain.discovery

import com.poly.herewego.data.discovery.model.PlaceEntity

interface DiscoveryUseCase {
    suspend fun execute(): Result<List<PlaceEntity>>
}