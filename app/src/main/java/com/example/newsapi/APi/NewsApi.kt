package com.example.newsapi.APi

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("news_api/api/news_api.php?category=1")
    fun politicsNews(): Call<NewsResponse>

    @GET("news_api/api/news_api.php?category=2")
    fun sportNews(): Call<NewsResponse>
    @GET("news_api/api/news_api.php?category=3")
    fun economicNews(): Call<NewsResponse>
}