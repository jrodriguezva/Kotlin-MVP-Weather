package com.jagrod.weather.di.component

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */

import android.app.Application
import android.content.Context
import com.jagrod.weather.data.database.IWeatherDatabaseService
import com.jagrod.weather.data.network.IImageNetworkService
import com.jagrod.weather.data.network.IWeatherNetworkService
import com.jagrod.weather.di.module.AppModule
import com.jagrod.weather.di.module.DatabaseModule
import com.jagrod.weather.di.module.NetworkModule
import com.jagrod.weather.di.module.RepositoryModule
import com.jagrod.weather.domain.IWeatherRepository
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import dagger.Component
import ru.solodovnikov.rx2locationmanager.RxLocationManager
import javax.inject.Singleton

/**
 * Created on 5/4/17.
 */
@Singleton @Component(modules = arrayOf(AppModule::class, RepositoryModule::class, NetworkModule::class, DatabaseModule::class)) public interface AppComponent {
    fun application(): Application

    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun userRepository(): IWeatherRepository

    fun userNetworkRepository(): IWeatherNetworkService

    fun userImageRepository(): IImageNetworkService

    fun userDatabaseRepository(): IWeatherDatabaseService

    fun rxLocationManager(): RxLocationManager
}