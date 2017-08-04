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