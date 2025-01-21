package com.poly.herewego.data.discovery

import android.content.Context
import com.poly.herewego.R
import com.poly.herewego.data.discovery.api.DiscoveryWebService
import com.poly.herewego.data.discovery.model.PlaceEntity
import com.poly.herewego.data.discovery.model.PlaceResponse
import com.poly.herewego.data.mountains.model.Mountain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val webService: DiscoveryWebService
) {
    private val _places = MutableStateFlow(emptyList<PlaceEntity>())
    val places: StateFlow<List<PlaceEntity>> = _places
    private var loaded = false

    /**
     * Loads the list of places from server
     */
    suspend fun loadPlaces(): StateFlow<List<PlaceEntity>> {
        if (!loaded) {
            loaded = true
            _places.value = withContext(Dispatchers.IO) {
                webService.getCities().map { PlaceEntity.from(it) }
            }
        }
        return places
    }
}