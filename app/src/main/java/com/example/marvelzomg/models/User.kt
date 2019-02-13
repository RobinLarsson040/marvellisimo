package com.example.marvelzomg.models

import com.google.firebase.database.PropertyName


data class User(
    @PropertyName("email") var email: String = "",
    @PropertyName("online") var online: Boolean = false,
    @PropertyName("series") var series: List<String>? = null,
    @PropertyName("characters") var characters: List<String>? = null,
    @PropertyName("messages") var messages: List<String>? = null
)
