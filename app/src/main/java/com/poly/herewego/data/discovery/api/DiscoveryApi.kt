package com.poly.herewego.data.discovery.api

import com.poly.herewego.data.discovery.model.PlaceResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DiscoveryWebService {

    private lateinit var api: DiscoveryApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.polydot.fr/cache/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DiscoveryApi::class.java)
    }

    suspend fun getCities(): List<PlaceResponse> {
        return api.getCities()
    }

    interface DiscoveryApi {
        @GET("cities500.json")
        suspend fun getCities(): List<PlaceResponse>
    }

}
