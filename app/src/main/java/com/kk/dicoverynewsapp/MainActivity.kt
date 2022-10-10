package com.kk.dicoverynewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kk.dicoverynewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)





    }
    fun buttonCanadaClick()
    {
        binding.textViewSelectedCountry.text="Canada Selected"
    }
    fun buttonUSClick()
    {
        binding.textViewSelectedCountry.text="US Selected"
    }
    fun buttonIndiaClick()
    {
        binding.textViewSelectedCountry.text="India Selected"
    }
}