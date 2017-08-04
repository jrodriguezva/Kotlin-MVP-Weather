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

