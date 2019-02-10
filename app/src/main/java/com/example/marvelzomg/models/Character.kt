package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Character(val id: Int, val name: String, val description : String, val resourceURI: String, val urls : List<Url>, val thumbnail: Image,
                     val comics: ComicList, val series: SeriesList) : Parcelable



