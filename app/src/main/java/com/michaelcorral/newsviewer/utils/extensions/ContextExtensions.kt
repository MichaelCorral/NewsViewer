package com.michaelcorral.newsviewer.utils.extensions

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Context.getDrawableCompat(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)