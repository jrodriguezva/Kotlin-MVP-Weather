package com.jagrod.weather.domain.interactor

import com.jagrod.weather.data.WeatherRepository
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class SaveCitySearched @Inject constructor(private val mRepository: WeatherRepository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : UseCase<Boolean, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<Boolean> {
        return Observable.just(mRepository.saveCity(params!!))
    }
}