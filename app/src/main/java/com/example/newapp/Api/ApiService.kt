package com.example.newapp.Api

import com.example.newapp.importantDetails.Apikey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search-news")
   suspend fun getAllNewsInIndiaInEnglish(
        @Query("language") language : String = "en" ,
        @Query("source-countries") country : String = "in" ,
        @Query("number") number : String = "40"
    ) : Response<NewsResponse>
    @GET("search-news")
     suspend fun getAllNewsInIndiaInHindi(
        @Query("language") language : String = "hi" ,
        @Query("source-countries") country : String = "in" ,
    ) : Response<NewsResponse>
    companion object{
        const val BASE_URL = "https://api.worldnewsapi.com/"
    }
}