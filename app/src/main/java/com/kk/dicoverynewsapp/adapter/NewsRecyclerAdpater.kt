package com.kk.dicoverynewsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kk.dicoverynewsapp.R
import com.kk.dicoverynewsapp.models.Article


class NewsRecyclerAdpater(private val context: Context, private val news: List<Article>)
    : RecyclerView.Adapter<NewsRecyclerAdpater.ViewHolder>() {

    private val TAG = "NewsAdapter"

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = news.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val new = news[position]
        holder.bind(new)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTitle = itemView.findViewById<TextView>(R.id.textviewNewsTitle)
        val ivNewsImage = itemView.findViewById<ImageView>(R.id.ivNewsImage)
        fun bind(news: Article) {
            textTitle.text = news.title
            Glide.with(context).load(news.url).into(ivNewsImage)
        }
    }
}