package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("height") @Expose var height: Int = 0, @SerializedName("url") @Expose var url: String? = null,
    @SerializedName("width") @Expose var width: Int = 0)