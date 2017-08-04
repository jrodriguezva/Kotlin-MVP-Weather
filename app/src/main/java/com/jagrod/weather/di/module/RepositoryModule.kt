package com.jagrod.weather.di.module

import com.jagrod.weather.data.WeatherRepository
import com.jagrod.weather.data.database.IWeatherDatabaseService
import com.jagrod.weather.data.network.IImageNetworkService
import com.jagrod.weather.data.network.IWeatherNetworkService
import com.jagrod.weather.domain.IWeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */

@Module class RepositoryModule {

    @Provides @Singleton internal fun provideRepository(service: IWeatherNetworkService,
                                                        serviceImage: IImageNetworkService,
                                                        dataservice: IWeatherDatabaseService): IWeatherRepository {
        return WeatherRepository(service, serviceImage, dataservice)
    }
}