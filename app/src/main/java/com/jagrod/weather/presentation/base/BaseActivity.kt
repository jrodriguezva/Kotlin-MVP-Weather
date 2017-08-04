package com.jagrod.weather.presentation.base

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jagrod.weather.WeatherApplication

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected val appComponent = WeatherApplication.appComponent
}