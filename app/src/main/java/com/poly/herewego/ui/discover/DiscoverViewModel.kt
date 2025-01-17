package com.poly.herewego.ui.discover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poly.herewego.data.discovery.DiscoverRepository
import com.poly.herewego.data.discovery.model.PlaceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverViewModel(private val repository: DiscoverRepository = DiscoverRepository()) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCities()
            placeState.value = response
        }
    }

    val placeState: MutableState<List<PlaceResponse>> = mutableStateOf(emptyList<PlaceResponse>())

    private suspend fun getCities(): List<PlaceResponse> {
        return repository.getCities().filter { it.pop.toInt() > 5000000 }
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCities()
            placeState.value = response
        }
    }
}