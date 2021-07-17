package com.michaelcorral.newsviewer.api.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
