package com.poly.herewego.data.discovery.api

import com.poly.herewego.data.discovery.model.PlaceResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

interface DiscoveryApi {
    @GET("cities500.json")
    suspend fun getCities(): List<PlaceResponse>
}
