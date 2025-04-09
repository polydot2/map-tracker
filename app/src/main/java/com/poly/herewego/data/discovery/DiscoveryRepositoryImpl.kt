package com.poly.herewego.data.discovery

import com.poly.herewego.data.discovery.api.DiscoveryApi
import com.poly.herewego.data.discovery.api.DiscoveryRepository
import com.poly.herewego.data.discovery.model.PlaceEntity
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(val api: DiscoveryApi) : DiscoveryRepository {
    override suspend fun getCities(): Result<List<PlaceEntity>> {
        return try {
            val response = api.getCities().map { PlaceEntity.from(it) }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}