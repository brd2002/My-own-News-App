package com.example.newapp.Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.Api.NewsResponse
import com.example.newapp.Repo.NewsRepo
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    var  res = mutableStateOf<NewsResponse?>(null)
    init {
        viewModelScope.launch {
            res.value = getEnglishNews(NewsRepo())
        }
    }
    suspend fun getEnglishNews(repo: NewsRepo) : NewsResponse ? {
        return  repo.getAllEnglishLanguageNews().body()
    }
    suspend fun getHindiNews(repo: NewsRepo) : NewsResponse ? {
        return  repo.getAllHindiLanguageNews().body()
    }
    suspend fun changeEnglishToHindi(){
        res.value = getHindiNews(NewsRepo())
    }
}