package com.kk.dicoverynewsapp

import android.app.Application
import com.kk.dicoverynewsapp.api.NewsService
import com.kk.dicoverynewsapp.api.RetrofitHelper
import com.kk.dicoverynewsapp.db.NewsDatabase
import com.kk.dicoverynewsapp.repository.NewsRepository

class ApplicationNews :Application() {
    lateinit var repository: NewsRepository

    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {

        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val database=NewsDatabase.getDatabase(applicationContext)
         repository = NewsRepository(newsService,database)
    }
}