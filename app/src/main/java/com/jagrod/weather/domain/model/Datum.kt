package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(@SerializedName("id") @Expose var id: String? = null,
                 @SerializedName("aspect") @Expose var aspect: Double = 0.toDouble(),
                 @SerializedName("assets") @Expose var assets: Assets? = null,
                 @SerializedName("contributor") @Expose var contributor: Contributor? = null,
                 @SerializedName("description") @Expose var description: String? = null,
                 @SerializedName("image_type") @Expose var imageType: String? = null,
                 @SerializedName("media_type") @Expose var mediaType: String? = null)