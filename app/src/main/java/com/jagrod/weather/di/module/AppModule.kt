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

package com.jagrod.weather.di.module

/**
 * @author Juan Agustín
 * @since 1/8/17.
 */
import android.app.Application
import android.content.Context
import com.jagrod.weather.data.executor.JobExecutor
import com.jagrod.weather.domain.executor.PostExecutionThread
import com.jagrod.weather.domain.executor.ThreadExecutor
import com.jagrod.weather.presentation.executor.UIThread
import dagger.Module
import dagger.Provides
import ru.solodovnikov.rx2locationmanager.RxLocationManager
import javax.inject.Singleton

/**
 * Created on 5/4/17.
 */
@Module class AppModule(val application: Application) {
    @Provides @Singleton internal fun providesApplication(): Application {
        return application
    }

    @Provides @Singleton internal fun provideApplicationContext(): Context {
        return application
    }

    @Provides @Singleton internal fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides @Singleton internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides @Singleton internal fun provideRxLocationManager(context: Context): RxLocationManager {
        return RxLocationManager(context)
    }

}