package com.michaelcorral.newsviewer.data

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import com.michaelcorral.newsviewer.data.local.NewsLocalDataSource
import com.michaelcorral.newsviewer.data.remote.NewsRemoteDataSource
import io.reactivex.Single

class NewsRepository(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRemoteDataSource, NewsLocalDataSource {

    override fun fetchNewsHeadlines(): Single<TopHeadlines> = remoteDataSource.fetchNewsHeadlines()

    override fun saveSourceCountry(country: String) = localDataSource.saveSourceCountry(country)
    override fun getSourceCountry() = localDataSource.getSourceCountry()
}