package com.example.unsplashphotos.network

import android.view.View
import com.squareup.moshi.Json

data class UnsplashPhoto(
    val id: String,
    @Json(name = "urls") val photoData: Photo,
    val description: String?,
    @Json(name = "user") val userData: User
) {
    companion object {
        fun UnsplashPhoto.showDescription(): Int {
            if (description.isNullOrEmpty())
                return View.GONE
            return View.VISIBLE
        }
    }
}

data class Photo(
    @Json(name = "regular") val imgSrcUrl: String
)

data class User(
    val name: String
)
