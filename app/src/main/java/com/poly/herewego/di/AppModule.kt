package com.poly.herewego.di


import android.app.Application
import com.poly.herewego.data.mountains.MountainsRepository
import com.poly.herewego.data.discovery.DiscoverRepositoryImpl
import com.poly.herewego.data.discovery.api.DiscoveryApi
import com.poly.herewego.data.discovery.api.DiscoveryRepository
import com.poly.herewego.domain.discovery.DiscoveryUseCase
import com.poly.herewego.domain.discovery.DiscoveryUseCaseImpl
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
    fun provideDiscoveryUseCase(discoveryRepository: DiscoveryRepository): DiscoveryUseCase {
        return DiscoveryUseCaseImpl(discoveryRepository)
    }

    @Provides
    @Singleton
    fun provideDiscoveryRepository(discoveryService: DiscoveryApi): DiscoveryRepository {
        return DiscoverRepositoryImpl(discoveryService)
    }

    @Provides
    @Singleton
    fun provideDiscoveryWebService(): DiscoveryApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.polydot.fr/cache/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DiscoveryApi::class.java)
    }
}
