package com.michaelcorral.newsviewer.screens.newslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.base.BaseViewModel
import com.michaelcorral.newsviewer.data.NewsRepository
import com.michaelcorral.newsviewer.utils.LoadingState
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class NewsListViewModel(private val repository: NewsRepository) : ViewModel(), BaseViewModel {

    private val compositeDisposable = CompositeDisposable()

    val articles = MutableLiveData<List<Article>>()
    val loadingState = MutableLiveData<LoadingState>()

    init {
        fetchNewsHeadlines()
    }

    private fun fetchNewsHeadlines() {
        compositeDisposable.add(
            repository
                .fetchNewsHeadlines()
                .doOnSubscribe { loadingState.value = LoadingState.LOADING }
                .doOnError { Timber.e(it) }
                .doFinally { loadingState.value = LoadingState.LOADED }
                .subscribe { newsHeadlines ->
                    articles.value = newsHeadlines.articles
                }
        )
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}