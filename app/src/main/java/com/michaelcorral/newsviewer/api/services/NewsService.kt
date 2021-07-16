package com.michaelcorral.newsviewer.api.services

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("/v2/top-headlines/sources?country={sourceCountry}")
    suspend fun getHeadlines(@Path("sourceCountry") sourceCountry: String)
}