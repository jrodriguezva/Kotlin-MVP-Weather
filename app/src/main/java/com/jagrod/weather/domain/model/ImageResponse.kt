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

package com.jagrod.weather.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(@SerializedName("page") @Expose var page: Int = 0,
                         @SerializedName("per_page") @Expose var perPage: Int = 0,
                         @SerializedName("total_count") @Expose var totalCount: Int = 0,
                         @SerializedName("search_id") @Expose var searchId: String? = null,
                         @SerializedName("data") @Expose var data: List<Datum> = emptyList(),
                         @SerializedName("spellcheck_info") @Expose var spellcheckInfo: SpellcheckInfo? = null)