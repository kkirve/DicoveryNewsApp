package com.kk.dicoverynewsapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kk.dicoverynewsapp.models.Article

@Dao
interface NewsDao {

    @Insert
     fun addNews(articles: List<Article> )

//    @Delete
//     fun deleteNew()
//
//    @Query("Select * from news")
//    suspend fun getNews():List<Article>
}