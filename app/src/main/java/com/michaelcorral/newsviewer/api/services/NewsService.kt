package com.michaelcorral.newsviewer.api.services

import com.michaelcorral.newsviewer.api.models.TopHeadlines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    fun getHeadlines(@Query("country") sourceCountry: String) : Single<TopHeadlines>
}