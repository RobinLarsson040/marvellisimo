package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicList(val available: Int, val returned: Int, val collectionURI: String, val items: List<ComicSummary>) : Parcelable