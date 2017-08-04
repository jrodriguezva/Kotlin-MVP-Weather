package com.jagrod.weather.presentation.model

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
data class Weather(val cityName: String, val temp: Double, val tempMax: Double, val tempMin: Double,
    val pressure: Double, val description: String)