package com.michaelcorral.newsviewer.screens.newslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.data.NewsRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val articles = MutableLiveData<List<Article>>()

    init {
        fetchNewsHeadlines()
    }

    private fun fetchNewsHeadlines() {
        compositeDisposable.add(
            repository
                .fetchNewsHeadlines()
                .subscribe { newsHeadlines ->
                    articles.value = newsHeadlines.articles

                }
        //Todo onerror
        //destroy composite
        )
    }
}