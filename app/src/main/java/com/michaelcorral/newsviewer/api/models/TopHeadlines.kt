package com.michaelcorral.newsviewer.api.models

data class TopHeadlines(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)