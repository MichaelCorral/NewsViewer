package com.michaelcorral.newsviewer.data.remote

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import com.michaelcorral.newsviewer.api.services.NewsService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class NewsRemoteDataSourceImpl(private val newsService: NewsService) : NewsRemoteDataSource {

    //TOdo get sharedpref country
    override fun fetchNewsHeadlines(): Single<TopHeadlines> {
        return newsService
            .getHeadlines("ca")
            .observeOn(AndroidSchedulers.mainThread())
    }
}