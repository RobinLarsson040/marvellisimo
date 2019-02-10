package com.example.marvelzomg.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDataContainer(val offset: Int, val limit: Int, val total: Int, val count: Int, val data: List<Character>) : Parcelable