package com.michaelcorral.newsviewer.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun ImageView.loadCircularIconFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).centerCrop().into(this)
}