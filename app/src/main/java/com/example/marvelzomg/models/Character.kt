package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Character(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val resourceURI: String = "",
    val urls: List<Url> = emptyList(),
    val thumbnail: Image? = null,
    val comics: ComicList? = null,
    val series: SeriesList? = null
) : Parcelable



