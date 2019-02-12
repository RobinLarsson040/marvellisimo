package com.example.marvelzomg.models

import com.google.firebase.database.PropertyName


data class User(
    @PropertyName("email") var email: String = "",
    @PropertyName("online")var online: Boolean = false
    )
