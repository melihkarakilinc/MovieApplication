package com.melihkarakilinc.moviesapplication.Utils

import com.google.gson.GsonBuilder
import com.melihkarakilinc.moviesapplication.Network.MovieAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {

    fun ApiService(): MovieAPI =
        Retrofit.Builder().baseUrl(ApiUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieAPI::class.java)
}