package com.kk.dicoverynewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kk.dicoverynewsapp.models.Article
import com.kk.dicoverynewsapp.models.NewsList

@Database(entities= [Article::class], version = 1, exportSchema = true)
abstract class NewsDatabase :RoomDatabase(){


    abstract  fun getNewsDao():NewsDao
      companion object
      {
          @Volatile
          private var INSTANCE :NewsDatabase?=null

          fun getDatabase(context :Context):NewsDatabase
          {
              if(INSTANCE==null)
              {
                  synchronized(this)
                  {
                      INSTANCE= Room.databaseBuilder(context,NewsDatabase::class.java,"newsDB").build()
                  }
              }

              return INSTANCE!!
          }
      }
}