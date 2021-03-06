package com.michaelcorral.newsviewer.base

interface BaseView {

    fun showLoading()
    fun hideLoading()

    fun showMessage(message: String)
}