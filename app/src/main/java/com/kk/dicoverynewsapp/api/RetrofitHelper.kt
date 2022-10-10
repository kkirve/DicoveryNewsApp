package com.kk.dicoverynewsapp.api

import android.app.Application
import android.content.Context
import android.os.Build
import com.kk.dicoverynewsapp.R
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import java.nio.charset.Charset
import java.security.KeyStore
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

object RetrofitHelper {

    private const val BASE_URL = "https://newsapi.org/v2/"

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a Custom Interceptor to apply Headers application wide
    val headerInterceptor = object : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            var request = chain.request()

            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .addHeader("Content-Type","application/json")
                .build()

            val response = chain.proceed(request)
            return response
        }
    }

    private val okHttp = OkHttpClient.Builder()
        .callTimeout(6000, TimeUnit.SECONDS)
        .addInterceptor(logger)


    //return retrofit instance
    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
    }



}