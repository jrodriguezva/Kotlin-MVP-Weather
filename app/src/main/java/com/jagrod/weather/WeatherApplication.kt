package com.jagrod.weather

import android.app.Application
import com.jagrod.weather.di.component.AppComponent
import com.jagrod.weather.di.component.DaggerAppComponent
import com.jagrod.weather.di.module.AppModule
import com.jagrod.weather.di.module.DatabaseModule
import com.jagrod.weather.di.module.NetworkModule
import com.jagrod.weather.di.module.RepositoryModule
import android.util.Base64

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
class WeatherApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).databaseModule(DatabaseModule()).networkModule(NetworkModule(BuildConfig.BASE_URL, BuildConfig.BASE_URL_IMAGES,BuildConfig.API_IMAGES)).repositoryModule(RepositoryModule()).build()
    }

}