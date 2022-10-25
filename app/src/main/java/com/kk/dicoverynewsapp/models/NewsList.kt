package com.kk.dicoverynewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class NewsList(

    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)