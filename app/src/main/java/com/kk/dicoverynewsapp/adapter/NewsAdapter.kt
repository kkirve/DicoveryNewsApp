package com.kk.dicoverynewsapp.adapter


import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kk.dicoverynewsapp.R
import com.kk.dicoverynewsapp.models.Article
import kotlinx.coroutines.NonDisposableHandle.parent


class NewsAdapter : ListAdapter<Article, NewsAdapter.newsViewHolder>(DiffUtil()) {


    class newsViewHolder(view: View,context: Context) : RecyclerView.ViewHolder(view) {
        val textTitle = view.findViewById<TextView>(R.id.textviewNewsTitle)
        val ivNewsImage = view.findViewById<ImageView>(R.id.ivNewsImage)
        val cnt=context
        fun bind(item: Article) {
            textTitle.text = item.title
            if (item.url !== null) {
                Glide.with(cnt)
                    .load(item.url)
                    .into(ivNewsImage)
            }else
            {
                ivNewsImage.setImageResource(R.drawable.ic_launcher_background)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return newsViewHolder(view,parent.context)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item)
    }


    class DiffUtil :androidx.recyclerview.widget.DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title==newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

}
