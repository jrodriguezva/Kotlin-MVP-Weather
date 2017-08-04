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

package com.jagrod.weather.data.network

import com.jagrod.weather.domain.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherNetworkService {
    @GET("data/2.5/weather") fun getWeather(@Query("q") city: String, @Query("lang") language: String,
                                            @Query("units") metric: String,
                                            @Query("appid") appId: String): Observable<WeatherResponse>

    @GET("data/2.5/weather") fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double,
                                            @Query("lang") language: String, @Query("units") metric: String,
                                            @Query("appid") appId: String): Observable<WeatherResponse>
}