package com.jagrod.weather.domain.model

data class Sys(var type: Int = 0, val id: Int = 0, val message: Double = 0.0, val country: String? = null,
    val sunrise: Int = 0, val sunset: Int = 0)