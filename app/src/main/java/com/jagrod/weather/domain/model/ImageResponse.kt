package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(@SerializedName("page") @Expose var page: Int = 0,
                         @SerializedName("per_page") @Expose var perPage: Int = 0,
                         @SerializedName("total_count") @Expose var totalCount: Int = 0,
                         @SerializedName("search_id") @Expose var searchId: String? = null,
                         @SerializedName("data") @Expose var data: List<Datum> = emptyList(),
                         @SerializedName("spellcheck_info") @Expose var spellcheckInfo: SpellcheckInfo? = null)