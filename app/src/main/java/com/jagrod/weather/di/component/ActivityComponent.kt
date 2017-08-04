package com.jagrod.weather.di.component

import com.jagrod.weather.di.module.ActivityModule
import com.jagrod.weather.di.scope.PerActivity
import com.jagrod.weather.presentation.module.MainActivity
import dagger.Component

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
@PerActivity @Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class)) interface ActivityComponent {

    fun inject(activity: MainActivity)
}