package com.michaelcorral.newsviewer.data.local

interface NewsLocalDataSource {

    fun saveSourceCountry(country: String)
    fun getSourceCountry(): String
}