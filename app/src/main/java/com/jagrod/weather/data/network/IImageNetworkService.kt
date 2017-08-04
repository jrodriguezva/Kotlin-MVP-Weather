package com.jagrod.weather.data.network

import com.jagrod.weather.domain.model.ImageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface IImageNetworkService {
    @GET("images/search") fun getImages( @Query("query") query: String,
                                        @Query("language") language: String,
                                        @Query("sort") sort: String): Observable<ImageResponse>
}