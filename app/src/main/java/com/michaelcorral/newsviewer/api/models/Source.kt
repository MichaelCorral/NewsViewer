package com.michaelcorral.newsviewer.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Source(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String
) : Parcelable