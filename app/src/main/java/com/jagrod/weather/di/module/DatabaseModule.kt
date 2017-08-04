package com.jagrod.weather.di.module

import android.content.Context
import com.jagrod.weather.data.database.IWeatherDatabaseService
import com.jagrod.weather.data.database.WeatherDatabaseService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
@Module class DatabaseModule {

    @Provides @Singleton internal fun provideStoreService(context: Context): IWeatherDatabaseService {
        return WeatherDatabaseService(context)
    }
}