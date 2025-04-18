package com.poly.herewego.domain.discovery

import com.poly.herewego.data.discovery.api.DiscoveryRepository
import com.poly.herewego.data.discovery.model.PlaceDto
import javax.inject.Inject

class DiscoveryUseCaseImpl @Inject constructor(val repository: DiscoveryRepository) : DiscoveryUseCase {
    override suspend fun execute(): Result<List<PlaceDto>> {
        return repository.getCities().onSuccess {
            return Result.success(it.take(4)) // override success ?
        }
    }
}