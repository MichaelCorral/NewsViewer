package com.michaelcorral.newsviewer.screens.newslist

import android.content.Intent
import android.os.Bundle
import com.michaelcorral.newsviewer.R
import com.michaelcorral.newsviewer.base.MvvmActivity

class NewsListActivity : MvvmActivity() {

    override fun getActivityLayout() = R.layout.activity_newslist

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)
    }
}