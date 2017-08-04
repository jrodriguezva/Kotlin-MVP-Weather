package com.jagrod.weather.presentation.module

import com.jagrod.weather.domain.model.Preview
import com.jagrod.weather.presentation.base.View
import com.jagrod.weather.presentation.model.Weather

interface WeatherView : View {

    fun showWeather(weather: Weather)

    fun showProgress()

    fun showImages(dataImages: Preview?)

    fun showError(message: String)

    fun hideProgress()

    fun setAdapterCities(cities: List<String>)
}