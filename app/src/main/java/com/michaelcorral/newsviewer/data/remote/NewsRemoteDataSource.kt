package com.michaelcorral.newsviewer.data.remote

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import io.reactivex.Single

interface NewsRemoteDataSource {

    fun fetchNewsHeadlines(): Single<TopHeadlines>
}