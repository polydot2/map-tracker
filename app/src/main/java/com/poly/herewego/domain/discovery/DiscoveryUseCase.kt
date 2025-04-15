package com.poly.herewego.domain.discovery

import com.poly.herewego.data.discovery.model.PlaceDto

interface DiscoveryUseCase {
    suspend fun execute(): Result<List<PlaceDto>>
}