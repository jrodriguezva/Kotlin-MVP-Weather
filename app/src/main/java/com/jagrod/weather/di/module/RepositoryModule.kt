/*
 * Copyright (c) 2017 Juan Agustín Rodríguez Valle Open Source Project.
 *
 * This file is part of Kotlin-MVP-Weather.
 *
 * Kotlin-MVP-Weather is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kotlin-MVP-Weather is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kotlin-MVP-Weather.  If not, see <http://www.gnu.org/licenses/>.
 */

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