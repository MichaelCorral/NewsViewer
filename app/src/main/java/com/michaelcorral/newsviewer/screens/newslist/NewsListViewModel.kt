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
    var sourceCountrySpinnerPosition = MutableLiveData<Int>()
    var refreshState = MutableLiveData<Boolean>()

    init {
        fetchNewsHeadlines()
        getSourceCountry()
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

    fun saveSourceCountry(country: String) {
        repository.saveSourceCountry(country)
        fetchNewsHeadlines()
    }

    private fun getSourceCountry() {
        val country = repository.getSourceCountry()

        if (country == "us" || country.isEmpty()) {
            sourceCountrySpinnerPosition.value = 0
        } else {
            sourceCountrySpinnerPosition.value = 1
        }
    }

    fun refreshArticles() {
        fetchNewsHeadlines()
        refreshState.value = false
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}