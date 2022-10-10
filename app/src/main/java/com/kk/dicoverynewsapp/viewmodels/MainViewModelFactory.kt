package com.kk.dicoverynewsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kk.dicoverynewsapp.repository.NewsRepository

class MainViewModelFactory(private val userRepository: NewsRepository, private val searchQuery :String, private val from:String, private val sortBy :String, private val apiKey:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository, searchQuery,from, sortBy ,apiKey) as T
    }
}