package com.poly.herewego.data.discovery

import android.content.Context
import com.poly.herewego.data.discovery.api.DiscoveryWebService
import com.poly.herewego.data.discovery.model.PlaceResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DiscoverRepository(
    @ApplicationContext val context: Context,
    @Inject val webService: DiscoveryWebService
) {
    suspend fun getCities(): List<PlaceResponse> {
        return webService.getCities()
    }
}