package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicList(
    val available: Int = 0,
    val returned: Int = 0,
    val collectionURI: String = "",
    val items: List<ComicSummary> = emptyList()
) : Parcelable