package com.kk.dicoverynewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kk.dicoverynewsapp.adapter.NewsAdapter
import com.kk.dicoverynewsapp.adapter.NewsRecyclerAdpater
import com.kk.dicoverynewsapp.api.NewsService
import com.kk.dicoverynewsapp.api.RetrofitHelper
import com.kk.dicoverynewsapp.databinding.ActivityMainBinding
import com.kk.dicoverynewsapp.repository.NewsRepository
import com.kk.dicoverynewsapp.viewmodels.MainViewModel
import com.kk.dicoverynewsapp.viewmodels.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        //improvements :possible to define in application class
        //below object will access main view model
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val repository = NewsRepository(newsService)
        val recyclerView = binding.listView
/*        val adapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter*/


        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository,"Canada","2022-10-10","popularity","22bb22af9cdd4fc5ab21d3eefc387b09")).get(MainViewModel::class.java)


        mainViewModel.news.observe(this, Observer {

            Log.d("Kiran",  "Kiran " + it.articles.toString())
            //add to adapter
            val adapter = NewsRecyclerAdpater(this,it.articles)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

           // adapter.submitList(it.articles)
           // mainViewModel.callWebService("United States","2022-10-10","popularity","22bb22af9cdd4fc5ab21d3eefc387b09");

        })

        buttonCanadaClick()

    }
    fun buttonCanadaClick()
    {
        binding.textViewSelectedCountry.text="Canada Selected"
    }
    fun buttonUSClick()
    {
        binding.textViewSelectedCountry.text="US Selected"
        mainViewModel.callWebService("United States","2022-10-10","popularity","22bb22af9cdd4fc5ab21d3eefc387b09");

    }
    fun buttonIndiaClick()
    {
        binding.textViewSelectedCountry.text="India Selected"
        mainViewModel.callWebService("India","2022-10-10","popularity","22bb22af9cdd4fc5ab21d3eefc387b09");

    }
}