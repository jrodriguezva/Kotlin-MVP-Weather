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

package com.jagrod.weather.di.module

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jagrod.weather.BuildConfig
import com.jagrod.weather.data.network.IImageNetworkService
import com.jagrod.weather.data.network.IWeatherNetworkService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
@Module class NetworkModule(private val mBaseApiUrl: String, private val mImageApiUrl: String,
                            private val auth: String) {

    @Provides @Singleton internal fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 Mb
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides @Singleton internal fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }

    @Provides @Singleton internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides @Singleton internal fun provideHttpAuthenticationInterceptor(): Interceptor {
        val interceptor = Interceptor({
                                          it.proceed(it.request().newBuilder().addHeader("Authorization", auth).build())
                                      })
        return interceptor
    }

    @Provides @Singleton internal @Named("RetrofitWeather") fun provideHttpClient(cache: Cache,
                                                                                  interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).cache(cache).build()
    }

    @Provides @Singleton internal @Named("RetrofitImages") fun provideHttpClientAuthentication(cache: Cache,
                                                                                               authentication: Interceptor,
                                                                                               interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(authentication).cache(cache).build()
    }

    @Provides @Singleton internal @Named("RetrofitWeather") fun provideRetrofit(gson: Gson,
                                                                                @Named("RetrofitWeather") client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(mBaseApiUrl).client(client).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    @Provides @Singleton @Named("RetrofitImages") internal fun provideRetrofitImages(gson: Gson,
                                                                                     @Named("RetrofitImages") client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(mImageApiUrl).client(client).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    @Provides @Singleton internal fun provideWeatherStoreService(
        @Named("RetrofitWeather") retrofit: Retrofit): IWeatherNetworkService {
        return retrofit.create<IWeatherNetworkService>(IWeatherNetworkService::class.java)
    }

    @Provides @Singleton internal fun provideImagesStoreService(
        @Named("RetrofitImages") retrofit: Retrofit): IImageNetworkService {
        return retrofit.create<IImageNetworkService>(IImageNetworkService::class.java)
    }
}