/*
 * Copyright (c) 2017 Juan Agustín Rodríguez Valle Open Source Project.
 *
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

package com.jagrod.weather.presentation.module

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.jagrod.weather.R
import com.jagrod.weather.R.layout
import com.jagrod.weather.di.component.DaggerActivityComponent
import com.jagrod.weather.domain.model.Preview
import com.jagrod.weather.presentation.base.BaseActivity
import com.jagrod.weather.presentation.model.Weather
import com.jagrod.weather.presentation.utils.hideKeyboard
import com.jagrod.weather.presentation.utils.loadUrl
import com.jakewharton.rxbinding2.widget.textChanges
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity(), WeatherView {

    override fun showWeather(weather: Weather) {
        tvTemperature.text = resources.getString(R.string.temperature, weather.temp)
        tvDescription.text = weather.description.capitalize()
    }

    override fun showProgress() {
        spinKit.visibility = View.VISIBLE
    }

    override fun showImages(dataImages: Preview?) {
        if (dataImages != null && dataImages.url != null) {
            imageView2.loadUrl(dataImages.url!!)
        }

    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun hideProgress() {
        spinKit.visibility = View.GONE
    }

    @Inject lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this)

        setListener()
    }

    private fun setListener() {

        ivLocation.setOnClickListener {
            ivLocation.hideKeyboard()
            Dexter.withActivity(this).withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).withListener(object : BaseMultiplePermissionsListener() {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    val allPermissions = report?.areAllPermissionsGranted() ?: false
                    if (allPermissions) {
                        tvTemperature.text = ""
                        tvDescription.text = ""
                        presenter.getLastLocation()

                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?,
                                                                token: PermissionToken?) {
                    token?.continuePermissionRequest()
                }

            }).check()
        }
        ivSearch.setOnClickListener {
            actvCity.text.let {
                ivSearch.hideKeyboard()
                presenter.searchWeatherCityName(it.toString())
            }
        }

        actvCity.textChanges().subscribe { charSequence ->
            if (!charSequence.isNullOrEmpty()) {
                ivSearch.hideKeyboard()
                ivLocation.visibility = View.GONE
                ivSearch.visibility = View.VISIBLE
            } else {
                ivLocation.visibility = View.VISIBLE
                ivSearch.visibility = View.GONE
            }
            tvTemperature.text = ""
            tvDescription.text = ""
        }
    }

    override fun setAdapterCities(cities: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
        actvCity.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
