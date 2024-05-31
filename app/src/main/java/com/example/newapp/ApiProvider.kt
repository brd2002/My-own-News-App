package com.example.newapp

import com.example.newapp.Api.ApiService
import com.example.newapp.importantDetails.Apikey
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    val client = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", Apikey.API_KEY)
            .build()
        chain.proceed(request)
    }.build()
    fun provideApi() = Retrofit.Builder().client(client).baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiService::class.java)
}