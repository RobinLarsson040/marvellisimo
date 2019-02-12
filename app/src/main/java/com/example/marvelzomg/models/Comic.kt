package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(val id: Int, val title: String, val description: String, val pageCount: Int,
                 val thumbnail: Image, val Url: Url, val comicSummary: ComicSummary ): Parcelable