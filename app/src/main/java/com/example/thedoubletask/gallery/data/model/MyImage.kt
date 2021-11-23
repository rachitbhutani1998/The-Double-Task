package com.example.thedoubletask.gallery.data.model

import com.google.gson.annotations.SerializedName

data class MyImage(
    @SerializedName("id") val id: String? = null,
    @SerializedName("urls") val url: ImageUrl? = null,
    @SerializedName("user") val user: User? = null
)

data class ImageUrl(
    @SerializedName("raw") val raw: String? = null,
    @SerializedName("full") val full: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)

data class User(
    @SerializedName("id") val userId: String? = null,
    @SerializedName("username") val userName: String? = null,
    @SerializedName("portfolio_url") val portfolio: String? = null,
    @SerializedName("instagram_username") val instagram: String? = null
)
