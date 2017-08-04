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