package com.jagrod.weather.data.database

import android.content.Context
import android.content.SharedPreferences
import com.jagrod.weather.presentation.utils.edit

class WeatherDatabaseService constructor(val context: Context) : IWeatherDatabaseService {

    val PREFS_FILENAME = "com.jagrod.weather.cities.prefs"
    val PREFS_CITIES = "cities"

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    override fun getCities(): List<String> {
        return prefs.getStringSet(PREFS_CITIES, emptySet()).toList()
    }

    override fun saveCity(name: String): Boolean {
        val citiesSet = prefs.getStringSet(PREFS_CITIES, emptySet()).toMutableSet()
        citiesSet.add(name)
        return prefs.edit { putStringSet(PREFS_CITIES, citiesSet) }
    }
}

