package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contributor(@SerializedName("id") @Expose var id: String? = null)