package com.example.unsplashphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.unsplash.com/"
private const val API_KEY = "XjVAMR5cDLYI2eZPcxkDvR8qvxZrFTspqqFF8TzWcng"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UnsplashApiService {

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("photos")
    suspend fun getPhotos(): List<UnsplashPhoto>
}

object UnsplashApi {
    val retrofitService : UnsplashApiService by lazy {
        retrofit.create(UnsplashApiService::class.java)
    }
}