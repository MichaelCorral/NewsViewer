package com.michaelcorral.newsviewer.screens.newslist

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaelcorral.newsviewer.R
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.base.MvvmActivity
import kotlinx.android.synthetic.main.activity_newslist.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsListActivity : MvvmActivity() {

    private val newsListViewModel by viewModel<NewsListViewModel>()

    override fun getActivityLayout() = R.layout.activity_newslist

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)

        displayNewsList()
    }

    private fun displayNewsList() {
        newsListViewModel.articles.observe(this, {
            newsListRecyclerview.layoutManager = LinearLayoutManager(this)

            newsListRecyclerview.adapter =
                NewsListAdapter(
                    articles = it,
                    onArticleClick = { item -> onArticleClick(item) })
        })
    }

    private fun onArticleClick(article: Article) {
        Timber.d("HELLO: ${article.title}")
    }
}