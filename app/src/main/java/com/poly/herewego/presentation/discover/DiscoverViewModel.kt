package com.poly.herewego.presentation.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poly.herewego.data.discovery.model.PlaceDto
import com.poly.herewego.domain.discovery.DiscoveryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoveryViewModel @Inject constructor(val discoveryUsecase: DiscoveryUseCase) : ViewModel() {
    private val ERROR_FETCHING_DATA: String = "Error"

    private val _uiState = MutableStateFlow<DiscoveryUiState>(DiscoveryUiState.Loading)
    val uiState: StateFlow<DiscoveryUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _uiState.value = DiscoveryUiState.Loading
            val result = discoveryUsecase.execute()
            _uiState.value = if (result.isSuccess) {
                DiscoveryUiState.Success(result.getOrNull() ?: listOf())
            } else {
                DiscoveryUiState.Error(result.exceptionOrNull()?.message ?: ERROR_FETCHING_DATA)
            }
        }
    }
}


/**
 * Sealed class view state
 */
sealed class DiscoveryUiState {
    object Loading : DiscoveryUiState()
    data class Success(val data: List<PlaceDto>) : DiscoveryUiState()
    data class Error(val message: String) : DiscoveryUiState()
}
