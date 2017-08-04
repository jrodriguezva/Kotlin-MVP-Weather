package com.jagrod.weather.presentation.module

import android.location.Location
import android.location.LocationManager
import android.support.annotation.NonNull
import com.jagrod.weather.domain.interactor.GetCitiesSearched
import com.jagrod.weather.domain.interactor.GetImagesSearched
import com.jagrod.weather.domain.interactor.GetLastLocation
import com.jagrod.weather.domain.interactor.GetWeatherByCoordinate
import com.jagrod.weather.domain.interactor.GetWeatherByName
import com.jagrod.weather.domain.interactor.RequestLocation
import com.jagrod.weather.domain.interactor.SaveCitySearched
import com.jagrod.weather.domain.model.Coordinate
import com.jagrod.weather.domain.model.ImageResponse
import com.jagrod.weather.domain.model.WeatherResponse
import com.jagrod.weather.presentation.base.Presenter
import com.jagrod.weather.presentation.base.View
import com.jagrod.weather.presentation.model.mapper.WeatherMapper
import com.jagrod.weather.presentation.utils.debug
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.rxkotlin.zipWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
class WeatherPresenter @Inject constructor(private val getLastLocation: GetLastLocation,
                                           private val getWeatherByName: GetWeatherByName,
                                           private val requestLocation: RequestLocation,
                                           private val getImagesSearched: GetImagesSearched,
                                           private val getWeatherByCoordinate: GetWeatherByCoordinate,
                                           private val saveCitySearched: SaveCitySearched,
                                           private val getCitiesSearched: GetCitiesSearched) : Presenter(getLastLocation, getWeatherByName, requestLocation, getWeatherByCoordinate) {

    companion object {
        private var mHomeView: WeatherView? = null
    }

    override fun onResume(view: View) {
        super.onResume(view)
        setView(view as WeatherView)
        getCitiesSearched.execute(ListCities())
    }

    override fun onDestroy() {
        super.onPause()
        mHomeView = null
    }

    private fun setView(@NonNull homeView: WeatherView) {
        mHomeView = homeView
    }

    fun getLastLocation() {
        if (!(subscribeBy?.isDisposed ?: false)) {
            subscribeBy?.dispose()
        }
        mHomeView!!.showProgress()
        getLastLocation.execute(LastLocationObserver(), LocationManager.PASSIVE_PROVIDER)
    }

    fun searchWeatherCityName(name: String) {
        if (!(subscribeBy?.isDisposed ?: false)) {
            subscribeBy?.dispose()
        }
        mHomeView!!.showProgress()
        getWeatherByName.execute(WeatherObserver(), name)
        saveCitySearched.execute(SaveCityObserver(), name)
        getCitiesSearched.execute(ListCities())
    }

    private fun onObserverError(error: Throwable) {
        mHomeView!!.hideProgress()
        error.message?.let { mHomeView!!.showError(it) }
    }

    private inner class LastLocationObserver : DisposableObserver<Location>() {

        override fun onNext(location: Location) {
            getWeatherByCoordinate.execute(WeatherObserver(), Coordinate(location.latitude, location.longitude))
        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
            requestLocation.execute(RequestLocationObserver(), LocationManager.PASSIVE_PROVIDER)
        }
    }

    private inner class RequestLocationObserver : DisposableObserver<Location>() {

        override fun onNext(location: Location) {
            getWeatherByCoordinate.execute(WeatherObserver(), Coordinate(location.latitude, location.longitude))
        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
        }
    }

    private inner class SaveCityObserver : DisposableObserver<Boolean>() {

        override fun onNext(saved: Boolean) {
            if (saved) getCitiesSearched.execute(ListCities())
        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
        }
    }

    private inner class WeatherObserver : DisposableObserver<WeatherResponse>() {

        override fun onNext(weather: WeatherResponse) {
            getImagesSearched.execute(ImagesObserver(), weather.name)
            mHomeView!!.showWeather(WeatherMapper.toVM(weather))
        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
        }
    }

    private inner class ListCities : DisposableObserver<List<String>>() {

        override fun onNext(cities: List<String>) {
            mHomeView!!.setAdapterCities(cities)
        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
        }
    }

    private var subscribeBy: Disposable? = null

    private inner class ImagesObserver : DisposableObserver<ImageResponse>() {

        override fun onNext(cities: ImageResponse) {
            mHomeView!!.hideProgress()
            val dataImages = cities.data
            val take = Observable.interval(0, 5, TimeUnit.SECONDS)
            if (!(subscribeBy?.isDisposed ?: false)) {
                subscribeBy?.dispose()
            }
            subscribeBy = dataImages.toObservable().zipWith(take).observeOn(AndroidSchedulers.mainThread()).subscribeBy {
                debug(it.first.assets?.preview)
                mHomeView!!.showImages(it.first.assets?.preview)
            }

        }

        override fun onComplete() {
        }

        override fun onError(error: Throwable) {
            onObserverError(error)
        }
    }

}