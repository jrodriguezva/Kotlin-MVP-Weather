package com.jagrod.weather.domain

import com.jagrod.weather.domain.model.ImageResponse
import com.jagrod.weather.domain.model.WeatherResponse
import io.reactivex.Observable

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
interface IWeatherRepository {
    fun getWeatherByCityName(cityName: String): Observable<WeatherResponse>
    fun getWeatherByGeographicCoordinates(lat: Double, lon: Double): Observable<WeatherResponse>
    fun getCities(): List<String>
    fun saveCity(name: String): Boolean
    fun getImages(name: String): Observable<ImageResponse>
}