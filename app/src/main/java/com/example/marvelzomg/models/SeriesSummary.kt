package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesSummary(val resourceURI: String, val name: String): Parcelable