package com.kk.dicoverynewsapp

import android.R.id
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.kk.dicoverynewsapp.Utils.WebserviceUtil
import com.kk.dicoverynewsapp.adapter.NewsRecyclerAdpater
import com.kk.dicoverynewsapp.api.NewsService
import com.kk.dicoverynewsapp.api.RetrofitHelper
import com.kk.dicoverynewsapp.databinding.ActivityMainBinding
import com.kk.dicoverynewsapp.repository.NewsRepository
import com.kk.dicoverynewsapp.viewmodels.MainViewModel
import com.kk.dicoverynewsapp.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    val TAG = "MainActivity"
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        analytics = Firebase.analytics
        //set up clickable for buttons
        setupButtonCanadaClick()
        setupButtonUSClick()
        setupButtonIndiaClick()

        //improvements :possible to define in application class
        //below object will access main view model
//        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
//        val repository = NewsRepository(newsService)

        //use repository with application class
        val repository=(application as ApplicationNews).repository
        val recyclerView = binding.listView

        //use param for calling viewmodel with factory to pass param
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository,getString(R.string.queryCanada),getString(R.string.queryFrom),getString(R.string.querySortBy),getString(R.string.queryAPIKey))).get(MainViewModel::class.java)


        mainViewModel.news.observe(this, Observer {
            //observe list here for new changes
            //add to adapter and set data to recyclerView
            val adapter = NewsRecyclerAdpater(this,it.articles)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

        })

        buttonCanadaClick()

    }

    private fun setupButtonIndiaClick() {
        binding.buttonIndia.setOnClickListener(View.OnClickListener {
            buttonIndiaClick()
        })
    }

    private fun setupButtonUSClick() {
        binding.buttonUS.setOnClickListener(View.OnClickListener {
            buttonUSClick()
        })
    }

    private fun setupButtonCanadaClick() {
        binding.buttonCanada.setOnClickListener(View.OnClickListener {
            buttonCanadaClick()
        })
    }


    fun buttonCanadaClick()
    {
        callLoadNewsAnalytics(getString(R.string.selectCanada))
        binding.textViewSelectedCountry.text=getString(R.string.selectCanada)
    }
    fun buttonUSClick()
    {

        callLoadNewsAnalytics(getString(R.string.selectUS))
        binding.textViewSelectedCountry.text=getString(R.string.selectUS)
        callWebService(getString(R.string.queryUS),getString(R.string.queryFrom),getString(R.string.querySortBy),getString(R.string.queryAPIKey));
    }
    fun buttonIndiaClick()
    {
        callLoadNewsAnalytics(getString(R.string.selectIndia))
        binding.textViewSelectedCountry.text=getString(R.string.selectIndia)
        callWebService(getString(R.string.queryIndia),getString(R.string.queryFrom),getString(R.string.querySortBy),getString(R.string.queryAPIKey));
    }
    fun callLoadNewsAnalytics(selectedButton:String)
    {
        //use firebase and fire analytics event
        val params = Bundle()
        params.putString("country", selectedButton)
        analytics.logEvent("loadNews",params)
    }

    fun testCrash()
    {
        //used to test crash analytics implementation
        throw RuntimeException("Test Crash") // Force a crash
    }
    fun callWebService(queryString:String,queryFrom :String,querySortBy :String,queryAPIKey :String )
    {
        if(WebserviceUtil.isValidWebCall(queryString,queryFrom,querySortBy,queryAPIKey)) {
            mainViewModel.callWebService(queryString, queryFrom, querySortBy, queryAPIKey);
        }
    }



}