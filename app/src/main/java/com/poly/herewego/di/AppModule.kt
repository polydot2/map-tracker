package com.poly.herewego.di


import android.app.Application
import com.poly.herewego.data.Map.MountainsRepository
import com.poly.herewego.data.discovery.DiscoverRepository
import com.poly.herewego.data.discovery.api.DiscoveryWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMountainRepository(app: Application): MountainsRepository {
        return MountainsRepository(app)
    }

    @Provides
    @Singleton
    fun provideDiscoveryRepository(app: Application, discoveryService: DiscoveryWebService): DiscoverRepository {
        return DiscoverRepository(app, discoveryService)
    }

    @Provides
    @Singleton
    fun provideDiscoveryWebService(app: Application): DiscoveryWebService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.polydot.fr/cache/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return DiscoveryWebService(retrofit.create(DiscoveryWebService.DiscoveryApi::class.java))
    }
}
