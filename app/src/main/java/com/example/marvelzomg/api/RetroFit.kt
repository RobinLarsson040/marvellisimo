package com.example.marvelzomg.api

import com.example.marvelzomg.services.MarvelService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetroFit {
    val MARVEL_BASE_URL = "https://gateway.marvel.com:443/v1/public/"


    val service: MarvelService = Retrofit.Builder()
        .baseUrl(MARVEL_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(MarvelService::class.java)

}

private fun getOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(addKeyAndHash())
    builder.addInterceptor(logging)
    val okHttpClient = builder.build()
    return okHttpClient
}

fun addKeyAndHash(): Interceptor = Interceptor { chain ->
    val params =
        mapOf("apikey" to "289e785710fa2e9154b3b7d3dbe99ef0", "hash" to "d73b3bf5b9096f88c3f7b9970d45164d", "ts" to "1")
    val originalRequest = chain.request()
    val urlWithParams = originalRequest.url().newBuilder()
        .apply { params.forEach { addQueryParameter(it.key, it.value) } }
        .build()
    val newRequest = originalRequest.newBuilder().url(urlWithParams).build()

    chain.proceed(newRequest)
}