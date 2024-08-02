package com.example.ourmenu.retrofit

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private const val BASE_URL = "https://bluesparrow.shop/dummy/"

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(NetworkModule.provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
