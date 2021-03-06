package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicSummary(val resourceURI: String = "", val name: String = "") : Parcelable