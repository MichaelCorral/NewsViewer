package com.michaelcorral.newsviewer.data.local

import com.michaelcorral.newsviewer.data.sharedpreferences.SharedPreferencesManager
import com.michaelcorral.newsviewer.data.sharedpreferences.SharedPreferencesManager.Key.*

class NewsLocalDataSourceImpl : NewsLocalDataSource {

    override fun saveSourceCountry(country: String) = SharedPreferencesManager.put(SourceCountry, country)
    override fun getSourceCountry() = SharedPreferencesManager.getString(SourceCountry)
}