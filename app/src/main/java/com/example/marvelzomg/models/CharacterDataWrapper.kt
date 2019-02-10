package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDataWrapper(val code: Int, val etag: String, val data: CharacterDataContainer) : Parcelable