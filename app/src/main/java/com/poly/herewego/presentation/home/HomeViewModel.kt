package com.poly.herewego.presentation.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.poly.herewego.R
import com.poly.herewego.data.Passport
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val allPassports = getAllPassport(context)
            _uiState.value = HomeUiState.Success(allPassports)
        }
    }

    private fun getAllPassport(context: Context): List<Passport> {
        return try {
            val inputStream = context.resources.openRawResource(R.raw.all_passports)
            val jsonString = InputStreamReader(inputStream).readText()
            inputStream.close()
            val type = object : TypeToken<List<Passport>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }
}


/**
 * Sealed class view state
 */
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<Passport>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
