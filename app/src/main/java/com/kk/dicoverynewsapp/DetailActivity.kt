package com.kk.dicoverynewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.kk.dicoverynewsapp.databinding.ActivityDetailBinding
import com.kk.dicoverynewsapp.databinding.ActivityMainBinding

class DetailActivity: AppCompatActivity()  {
    private lateinit var binding: ActivityDetailBinding
    val TAG = "DetailedActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail)

        setupArticleAndListener()

    }

    private fun setupArticleAndListener() {
        val url:String=intent.getStringExtra(getString(R.string.paramUrl)).toString()

        val content:String=intent.getStringExtra(getString(R.string.paramContent)).toString()
        val title:String=intent.getStringExtra(getString(R.string.paramTitle)).toString()
        val author:String=intent.getStringExtra(getString(R.string.paramAuthor)).toString()
        val urltoimage:String=intent.getStringExtra(getString(R.string.paramUrlToImage)).toString()
        val description:String=intent.getStringExtra(getString(R.string.paramDescription)).toString()
        val publishedat:String=intent.getStringExtra(getString(R.string.paramPublishedat)).toString()

        binding.tvNewsContent.text=content
        binding.tvNewsTitle.text=title
        binding.tvNewsSource.text=author
        binding.tvNewsDesc.text=description
        binding.tvTime.text=publishedat


        Glide.with(this).load(urltoimage).into(binding.ivNewsImage)

        binding.toolbar.setOnClickListener()
        {
            finish()
        }
    }


}