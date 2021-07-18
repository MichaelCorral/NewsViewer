package com.michaelcorral.newsviewer.data.remote

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import com.michaelcorral.newsviewer.api.services.NewsService
import com.michaelcorral.newsviewer.data.sharedpreferences.SharedPreferencesManager
import com.michaelcorral.newsviewer.data.sharedpreferences.SharedPreferencesManager.Key.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

private const val DEFAULT_SOURCE = "us"

class NewsRemoteDataSourceImpl(private val newsService: NewsService) : NewsRemoteDataSource {

    override fun fetchNewsHeadlines(): Single<TopHeadlines> {
        return newsService
            .getHeadlines(getSourceCountry())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getSourceCountry(): String {
        var sourceCountry = SharedPreferencesManager.getString(SourceCountry)
        if (sourceCountry.isEmpty()) {
            sourceCountry = DEFAULT_SOURCE
        }

        return sourceCountry
    }
}