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

data class Datum(@SerializedName("id") @Expose var id: String? = null,
                 @SerializedName("aspect") @Expose var aspect: Double = 0.toDouble(),
                 @SerializedName("assets") @Expose var assets: Assets? = null,
                 @SerializedName("contributor") @Expose var contributor: Contributor? = null,
                 @SerializedName("description") @Expose var description: String? = null,
                 @SerializedName("image_type") @Expose var imageType: String? = null,
                 @SerializedName("media_type") @Expose var mediaType: String? = null)