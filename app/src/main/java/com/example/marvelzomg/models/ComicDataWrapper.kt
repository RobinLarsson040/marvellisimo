package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDataWrapper(val code: Int = 0, val etag: String = "", val data: ComicDataContainer? = null): Parcelable