package com.poly.herewego.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poly.herewego.R
import com.poly.herewego.data.discovery.model.PlaceEntity
import com.poly.herewego.domain.discovery.DiscoveryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val ERROR_FETCHING_DATA: String = "Error"

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = R.raw.all_passports.readBytes().decodeToString()
            _uiState.value = if (result.isSuccess) {
                HomeUiState.Success(result.getOrNull() ?: listOf())
            } else {
                HomeUiState.Error(result.exceptionOrNull()?.message ?: ERROR_FETCHING_DATA)
            }
        }
    }
}


/**
 * Sealed class view state
 */
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<PlaceEntity>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
