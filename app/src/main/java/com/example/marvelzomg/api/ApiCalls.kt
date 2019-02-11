package com.example.marvelzomg.api

import com.example.marvelzomg.adapters.CharacterListAdapter
import com.example.marvelzomg.models.CharacterDataWrapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient


object ApiCalls {

    private val baseUrl = "https://gateway.marvel.com:443/v1/public/"
    private val httpClient = OkHttpClient.Builder()


}