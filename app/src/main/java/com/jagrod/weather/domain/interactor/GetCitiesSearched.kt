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
class GetCitiesSearched @Inject constructor(private val mRepository: WeatherRepository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : UseCase<List<String>, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<List<String>> {
        return Observable.just(mRepository.getCities())
    }
}