package com.kk.dicoverynewsapp.api

import com.kk.dicoverynewsapp.models.NewsList

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Streaming

interface NewsService {

    //call web api with this end point and param
    @GET("/v2/everything")
    suspend fun getNews(@Query("q") queryString: String,
                        @Query("from") from :String,
                        @Query("sortBy") sortBy :String,
                        @Query("apiKey") apiKey :String
                         ): Response<NewsList>

}