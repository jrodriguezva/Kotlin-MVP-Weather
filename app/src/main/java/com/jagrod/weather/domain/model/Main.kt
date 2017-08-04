package com.jagrod.weather.domain.model

data class Main(val temp: Double = 0.0, val pressure: Double = 0.0, val humidity: Double = 0.0, val tempMin: Double = 0.0,
    val tempMax: Double = 0.0)