package com.jagrod.weather.data.database

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
interface IWeatherDatabaseService {
    fun getCities(): List<String>
    fun saveCity(name: String): Boolean
}