package com.poly.herewego.di


import android.app.Application
import com.poly.herewego.data.Map.MountainsRepository
import com.poly.herewego.data.discovery.DiscoverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideDiscoveryRepository(app: Application): DiscoverRepository {
        return DiscoverRepository(app)
    }

    @Provides
    @Singleton
    fun provideDiscoveryService(app: Application): DiscoverRepository {
        return DiscoverRepository(app)
    }
}
