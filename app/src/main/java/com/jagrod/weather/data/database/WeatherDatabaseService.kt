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

