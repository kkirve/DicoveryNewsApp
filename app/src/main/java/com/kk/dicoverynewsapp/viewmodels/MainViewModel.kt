package com.kk.dicoverynewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kk.dicoverynewsapp.models.NewsList
import com.kk.dicoverynewsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: NewsRepository,
                    searchQuery :String,
                    from:String,
                    sortBy :String,
                    apiKey:String) : ViewModel() {

    init {
        callWebService(searchQuery,from,sortBy,apiKey)
    }

    fun callWebService(searchQuery :String, from:String, sortBy :String,apiKey:String)
    {
        //get news is suspend function that's why launch coroutine
        //IO operation //news
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getNews(searchQuery,from,sortBy,apiKey)
        }
    }

    val news: LiveData<NewsList>
        get() = userRepository.news

    fun clearNews()
    {

    }

}