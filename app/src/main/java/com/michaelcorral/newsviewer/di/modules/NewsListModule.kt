package com.michaelcorral.newsviewer.di.modules

import com.michaelcorral.newsviewer.screens.newslist.NewsListViewModel
import org.koin.dsl.module

val newsListModule = module {
    single { NewsListViewModel(get()) }
}