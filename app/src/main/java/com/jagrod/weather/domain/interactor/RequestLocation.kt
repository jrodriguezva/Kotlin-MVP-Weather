/*
 * Copyright (c) 2017 Juan Agustín Rodríguez Valle Open Source Project.
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

import android.location.Location
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import io.reactivex.Observable
import ru.solodovnikov.rx2locationmanager.LocationTime
import ru.solodovnikov.rx2locationmanager.RxLocationManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class RequestLocation @Inject constructor(private val mRepository: RxLocationManager, threadExecutor: ThreadExecutor,
                                          postExecutionThread: PostExecutionThread) : UseCase<Location, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<Location> {
        return mRepository.requestLocation(params!!, LocationTime(1, TimeUnit.HOURS)).toObservable()
    }
}