package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    val id: Int = 0, val title: String = "", val description: String = "No Description", val pageCount: Int = 0,
    val thumbnail: Image? = null, val Url: Url? = null, val comicSummary: ComicSummary? = null): Parcelable