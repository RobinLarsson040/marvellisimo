package com.example.marvelzomg.service

import com.example.marvelzomg.models.CharacterDataWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getAllCharacters()

    @GET
    fun getAllCharactersByName(@Query("nameStartsWith") nameStartsWith: String, @Query("limit") limit: Int,
                             @Query("offset") offset: Int): Observable<CharacterDataWrapper>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int)

    @GET("comics")
    fun getAllComics()

    @GET("comics")
    fun getAllComicsByName(@Query("nameStartsWith") nameStartsWith: String, @Query("limit") limit: Int,
                           @Query("offset") offset: Int)

}