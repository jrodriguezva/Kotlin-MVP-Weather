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