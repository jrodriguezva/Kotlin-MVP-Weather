package com.jagrod.weather.presentation.model.mapper

import com.jagrod.weather.domain.model.WeatherResponse
import com.jagrod.weather.presentation.model.Weather

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
object WeatherMapper {
    fun toVM(weather: WeatherResponse): Weather {
        return Weather(cityName = weather.name, temp = weather.main?.temp ?: 0.0, tempMax = weather.main?.tempMax ?: 0.0, tempMin = weather.main?.tempMin ?: 0.0, description = weather.weather[0].description ?: "", pressure = weather.main?.tempMax ?: 0.0)
    }
}