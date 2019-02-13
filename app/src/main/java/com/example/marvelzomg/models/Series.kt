package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val id: Int, val title: String, val description: String, val urls: MutableList<Url>,
    val comics: ComicList, val thumbnail: Image, val startYear: Int, val endYear: Int
) : Parcelable