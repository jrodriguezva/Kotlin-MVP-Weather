package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Assets(@SerializedName("preview") @Expose val preview: Preview?,
                  @SerializedName("small_thumb") @Expose val smallThumb: SmallThumb? = null,
                  @SerializedName("large_thumb") @Expose val largeThumb: LargeThumb? = null)