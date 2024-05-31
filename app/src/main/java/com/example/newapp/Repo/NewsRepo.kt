package com.example.newapp.Repo

import com.example.newapp.Api.NewsResponse
import com.example.newapp.ApiProvider
import retrofit2.Response

class NewsRepo {
    suspend fun getAllEnglishLanguageNews(): Response<NewsResponse>{
        return ApiProvider.provideApi().getAllNewsInIndiaInEnglish()
    }
    suspend fun getAllHindiLanguageNews(): Response<NewsResponse>{
        return ApiProvider.provideApi().getAllNewsInIndiaInHindi()
    }

}