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