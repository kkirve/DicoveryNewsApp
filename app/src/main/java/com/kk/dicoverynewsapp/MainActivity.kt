package com.kk.dicoverynewsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //set up clickable for buttons
        setupButtonCanadaClick()
        setupButtonUSClick()
        setupButtonIndiaClick()

        //improvements :possible to define in application class
        //below object will access main view model
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val repository = NewsRepository(newsService)
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
        binding.textViewSelectedCountry.text=getString(R.string.selectCanada)
    }
    fun buttonUSClick()
    {
        binding.textViewSelectedCountry.text=getString(R.string.selectUS)
        mainViewModel.callWebService(getString(R.string.queryUS),getString(R.string.queryFrom),getString(R.string.querySortBy),getString(R.string.queryAPIKey));
    }
    fun buttonIndiaClick()
    {
        binding.textViewSelectedCountry.text=getString(R.string.selectIndia)
        mainViewModel.callWebService(getString(R.string.queryIndia),getString(R.string.queryFrom),getString(R.string.querySortBy),getString(R.string.queryAPIKey));
    }
}