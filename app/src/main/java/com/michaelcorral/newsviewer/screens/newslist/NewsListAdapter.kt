package com.michaelcorral.newsviewer.screens.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.michaelcorral.newsviewer.R
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.application.GlideApp
import kotlinx.android.synthetic.main.article_item.view.*

class NewsListAdapter(
    private val articles: List<Article>,
    private val onArticleClick: (item: Article) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val items = mutableListOf<Article>()

    fun addItem(item: Article) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun updateItem(item: Article, index: Int) {
        notifyItemChanged(index, item)
    }

    fun replaceItems(items: List<Article>) {
        clearItems()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article) {
            GlideApp.with(itemView)
                .load(article.urlToImage)
                .transform(RoundedCorners(16))
                .into(itemView.articleItemImageView)

            itemView.articleTextViewTitle.text = article.title

            itemView.articleTextViewSource.text = article.source.name

            itemView.setOnClickListener {
                onArticleClick(article)
            }
        }
    }

}