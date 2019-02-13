package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesDataWrapper(val code: Int, val data: SeriesDataContainer): Parcelable