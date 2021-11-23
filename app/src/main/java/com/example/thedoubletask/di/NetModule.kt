package com.example.thedoubletask.di

import com.example.thedoubletask.MyApplication
import com.example.thedoubletask.gallery.data.service.GalleryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = "VV4GFl7faNy_SFgqChHVTd_6ee74vlBqYf-HVOrb6g0"
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request
                .newBuilder()
                .addHeader("Authorization", "Client-ID $CLIENT_ID")
                .build()
            chain.proceed(newRequest)
        }.build()
    }

    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideGalleryService(retrofit: Retrofit): GalleryService {
        return retrofit.create(GalleryService::class.java)
    }

}