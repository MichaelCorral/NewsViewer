package com.michaelcorral.newsviewer.di.modules

import com.michaelcorral.newsviewer.data.NewsRepository
import com.michaelcorral.newsviewer.data.local.NewsLocalDataSource
import com.michaelcorral.newsviewer.data.local.NewsLocalDataSourceImpl
import com.michaelcorral.newsviewer.data.remote.NewsRemoteDataSource
import com.michaelcorral.newsviewer.data.remote.NewsRemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single<NewsRemoteDataSource> { NewsRemoteDataSourceImpl(get()) }
    single<NewsLocalDataSource> { NewsLocalDataSourceImpl() }
    single {
        NewsRepository(
            get(),
            get()
        )
    }
}