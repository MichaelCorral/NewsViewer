package com.michaelcorral.newsviewer.data

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import com.michaelcorral.newsviewer.data.remote.NewsRemoteDataSource
import io.reactivex.Single

class NewsRepository(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRemoteDataSource {

    override fun fetchNewsHeadlines(): Single<TopHeadlines> = remoteDataSource.fetchNewsHeadlines()

}