package com.example.newsapi.APi

import com.example.newsapi.Models.News
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("news")
    var news: List<News>
)
