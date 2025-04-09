package com.poly.herewego.domain.discovery

import com.poly.herewego.data.discovery.api.DiscoveryRepository
import com.poly.herewego.data.discovery.model.PlaceEntity
import javax.inject.Inject

class DiscoveryUseCaseImpl @Inject constructor(val repository: DiscoveryRepository) : DiscoveryUseCase {
    override suspend fun execute(): Result<List<PlaceEntity>> {
        return repository.getCities().onSuccess {
            return Result.success(it.take(4)) // override success ?
        }
    }
}