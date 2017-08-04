package com.jagrod.weather.domain.interactor

import com.jagrod.weather.domain.IWeatherRepository
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import com.jagrod.weather.domain.model.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class GetWeatherByName @Inject constructor(private val mRepository: IWeatherRepository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : UseCase<WeatherResponse, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<WeatherResponse> {
        return mRepository.getWeatherByCityName(params!!)
    }
}