package com.example.newsapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.APi.NewsFetcher
import com.example.newsapi.Models.News

class NewsViewModel : ViewModel() {

    val politicsNewsLiveData: LiveData<List<News>>
    val sportNewsLiveData: LiveData<List<News>>
    val econoimNewsLiveData: LiveData<List<News>>

    init {
        politicsNewsLiveData= NewsFetcher().politicsNews()
        sportNewsLiveData= NewsFetcher().sportNews()
        econoimNewsLiveData= NewsFetcher().economicNews()
    }
}