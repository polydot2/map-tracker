package com.poly.herewego.presentation.passport

import android.content.Context
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
class PassportViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {
    private val defaultPassport = Passport("id", "2020", "", "0xFFFFF", "", 0f, 0f, "", listOf())
    private val _uiState = MutableStateFlow<PassportUiState>(PassportUiState.Loading)
    val uiState: StateFlow<PassportUiState> = _uiState

    fun fetchData(passportId: String) {
        viewModelScope.launch {
            val result = readFromRaw(context, passportId)
            _uiState.value = PassportUiState.Success(result)
        }
    }

    private fun readFromRaw(context: Context, passportId: String): Passport {
        return try {
            context.resources.openRawResource(R.raw.all_passports).use { inputStream ->
                val jsonString = InputStreamReader(inputStream).readText()
                val passports = Gson().fromJson<List<Passport>>(jsonString, object : TypeToken<List<Passport>>() {}.type)
                passports.firstOrNull { it.id == passportId } ?: defaultPassport
            }
        } catch (e: Exception) {
            e.printStackTrace()
            defaultPassport
        }
    }
}


sealed class PassportUiState {
    object Loading : PassportUiState()
    data class Success(val passport: Passport) : PassportUiState()
    data class Error(val message: String) : PassportUiState()
}
