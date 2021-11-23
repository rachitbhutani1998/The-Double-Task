package com.example.thedoubletask.gallery.data.service

import com.example.thedoubletask.gallery.data.model.MyImage
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {

    @GET("photos")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ): List<MyImage>
}