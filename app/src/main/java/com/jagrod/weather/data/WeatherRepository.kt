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