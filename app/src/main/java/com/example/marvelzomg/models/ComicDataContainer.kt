package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDataContainer(val offset: Int = 0, val limit: Int = 20, val total: Int = 0, val count: Int = 0, val results: List<Comic> = emptyList()) : Parcelable