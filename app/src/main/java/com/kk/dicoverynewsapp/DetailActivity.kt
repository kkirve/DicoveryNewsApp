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
    val PARAM_ARTICLE = "param-article"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail)


        setupArticleAndListner()



    }

    private fun setupArticleAndListner() {
        val url:String=intent.getStringExtra("url").toString()

        val content:String=intent.getStringExtra("content").toString()
        val title:String=intent.getStringExtra("title").toString()
        val author:String=intent.getStringExtra("author").toString()
        val urltoimage:String=intent.getStringExtra("urltoimage").toString()
        val description:String=intent.getStringExtra("description").toString()
        val publishedat:String=intent.getStringExtra("publishedat").toString()

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