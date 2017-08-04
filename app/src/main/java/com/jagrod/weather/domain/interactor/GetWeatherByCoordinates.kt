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

package com.jagrod.weather.domain.interactor

import com.jagrod.weather.domain.IWeatherRepository
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import com.jagrod.weather.domain.model.Coordinate
import com.jagrod.weather.domain.model.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class GetWeatherByCoordinate @Inject constructor(private val mRepository: IWeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : UseCase<WeatherResponse, Coordinate>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Coordinate?): Observable<WeatherResponse> {
        return mRepository.getWeatherByGeographicCoordinates(params?.lat ?: 0.0, params?.lon ?: 0.0)
    }
}

