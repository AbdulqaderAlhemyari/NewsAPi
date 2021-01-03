package com.example.newsapi.APi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapi.Models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFetcher {

    private val newsApi:NewsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.105/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApi = retrofit.create(NewsApi::class.java)
    }

    fun politicsNews(): LiveData<List<News>> {
        val responseLiveData: MutableLiveData<List<News>> = MutableLiveData()
        val request: Call<NewsResponse> = newsApi.politicsNews()

        request.enqueue(object : Callback<NewsResponse> {

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("fetchNews", "Response received correctly")
                val newsResponse: NewsResponse? = response.body()
                var news: List<News> = newsResponse?.news
                    ?: mutableListOf()
                responseLiveData.value = news
            }
        })

        return responseLiveData
    }

    fun sportNews(): LiveData<List<News>> {
        val responseLiveData: MutableLiveData<List<News>> = MutableLiveData()
        val request: Call<NewsResponse> = newsApi.sportNews()

        request.enqueue(object : Callback<NewsResponse> {

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("fetchNews", "Response received correctly")
                val newsResponse: NewsResponse? = response.body()
                var news: List<News> = newsResponse?.news
                    ?: mutableListOf()
                responseLiveData.value = news
            }
        })

        return responseLiveData
    }

    fun economicNews(): LiveData<List<News>> {
        val responseLiveData: MutableLiveData<List<News>> = MutableLiveData()
        val request: Call<NewsResponse> = newsApi.economicNews()

        request.enqueue(object : Callback<NewsResponse> {

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("fetchNews", "Response received correctly")
                val newsResponse: NewsResponse? = response.body()
                var news: List<News> = newsResponse?.news
                    ?: mutableListOf()
                responseLiveData.value = news
            }
        })

        return responseLiveData
    }


}