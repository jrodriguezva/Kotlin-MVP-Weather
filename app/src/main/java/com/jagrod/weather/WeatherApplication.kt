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