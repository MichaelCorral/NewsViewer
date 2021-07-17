package com.michaelcorral.newsviewer.application

import android.app.Application
import com.michaelcorral.newsviewer.BuildConfig
import com.michaelcorral.newsviewer.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
        initializeKoin()
        initializeSharedPreferences()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MvvmApplication)
            modules(listOf(networkModule))
        }
    }

    private fun initializeSharedPreferences() {
//        SharedPreferencesManager.initialize(this)
    }
}