package com.example.marvelzomg.services

import com.example.marvelzomg.models.CharacterDataWrapper
import com.example.marvelzomg.models.ComicDataWrapper
import com.example.marvelzomg.models.SeriesDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getAllCharacters(@Query("offset") offset: Int): Single<CharacterDataWrapper>

    @GET("characters")
    fun getAllCharactersByName(
        @Query("nameStartsWith") nameStartsWith: String, @Query("offset") offset: Int
    ): Single<CharacterDataWrapper>

    @GET("characters/{id}")
    fun getCharacterById(@Path("id") id: Int): Single<CharacterDataWrapper>

    @GET("comics")
    fun getAllComics(): Single<ComicDataWrapper>

    @GET("comics")
    fun getAllComicsByName(
        @Query("nameStartsWith") nameStartsWith: String, @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ComicDataWrapper>

    @GET("comics/{id}")
    fun getComicById(@Path("id") id: Int): Single<CharacterDataWrapper>

    @GET("series")
    fun getAllSeries(@Query("offset") offset: Int): Single<SeriesDataWrapper>

    @GET("series")
    fun getAllSeriesByTitle(@Query("titleStartsWith") titleStartsWith: String, @Query("offset") offset: Int): Single<SeriesDataWrapper>

    @GET("series/{id}")
    fun getSeriesById(@Path("id") id: Int): Single<SeriesDataWrapper>

}