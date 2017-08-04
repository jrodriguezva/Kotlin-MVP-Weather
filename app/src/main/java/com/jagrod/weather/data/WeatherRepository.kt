/*
 * Copyright (c) 2017 Juan Agustín Rodríguez Valle Open Source Project.
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

package com.jagrod.weather.data

import com.jagrod.weather.BuildConfig
import com.jagrod.weather.data.database.IWeatherDatabaseService
import com.jagrod.weather.data.network.IImageNetworkService
import com.jagrod.weather.data.network.IWeatherNetworkService
import com.jagrod.weather.domain.IWeatherRepository
import com.jagrod.weather.domain.model.ImageResponse
import com.jagrod.weather.domain.model.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val networkService: IWeatherNetworkService,
                                            private val imageNetworkService: IImageNetworkService,
                                            private val databaseService: IWeatherDatabaseService) : IWeatherRepository {
    override fun getWeatherByCityName(
        cityName: String): Observable<WeatherResponse> = networkService.getWeather(cityName, "es", "metric", BuildConfig.APP_ID)

    override fun getWeatherByGeographicCoordinates(lat: Double,
                                                   lon: Double): Observable<WeatherResponse> = networkService.getWeather(lat, lon, "es","metric", BuildConfig.APP_ID)

    override fun getCities(): List<String> = databaseService.getCities()

    override fun saveCity(name: String): Boolean = databaseService.saveCity(name)

    override fun getImages(
        name: String): Observable<ImageResponse> = imageNetworkService.getImages(name, "es", "popular")
}