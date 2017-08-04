package com.jagrod.weather.domain.interactor

import android.location.Location
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import io.reactivex.Observable
import ru.solodovnikov.rx2locationmanager.LocationTime
import ru.solodovnikov.rx2locationmanager.RxLocationManager
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class GetLastLocation @Inject constructor(private val mRepository: RxLocationManager, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : UseCase<Location, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<Location> {
        return mRepository.getLastLocation(params!!, LocationTime.oneHour()).toObservable()
    }
}