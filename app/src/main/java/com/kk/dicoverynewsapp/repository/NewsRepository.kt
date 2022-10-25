package com.kk.dicoverynewsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kk.dicoverynewsapp.api.NewsService
import com.kk.dicoverynewsapp.db.NewsDatabase
import com.kk.dicoverynewsapp.models.NewsList


//Repository used in between View model and web api interface to manage data
class NewsRepository(private val userService: NewsService,private val newsDatabase: NewsDatabase) //if need database add one more param
{

    //News data for testing purpose
    //mutable live data possible to change values from it
    private val newsLiveData = MutableLiveData<NewsList>()

    //live data not possible to change values after first initialize
    //Share live data to out side the repository so not possible to change that from out side repository
    val news: LiveData<NewsList>
        get() = newsLiveData

    suspend fun getNews(searchQuery: String,from :String,sortBy :String,apiKey :String) {
        //call to web api user interface
       // newsLiveData.postValue(null)
        val result = userService.getNews(searchQuery,from,sortBy,apiKey)
        //null safety used
        if (result?.body() != null) {
            //return to view  model
            newsDatabase.getNewsDao().addNews(result.body()!!.articles)
            newsLiveData.postValue(result.body())

        }

    }

}