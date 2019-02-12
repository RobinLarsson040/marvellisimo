package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDataWrapper(val code: Int, val etag: String, val data: ComicDataContainer): Parcelable