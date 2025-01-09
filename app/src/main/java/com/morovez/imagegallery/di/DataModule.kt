package com.morovez.imagegallery.di

import com.morovez.imagegallery.data.ImageAPI
import com.morovez.imagegallery.data.ImageRepositoryImpl
import com.morovez.imagegallery.data.ImageService
import com.morovez.imagegallery.data.ImageServiceImpl
import com.morovez.imagegallery.domain.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Reusable
    @Binds
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository

    @Reusable
    @Binds
    fun bindImageService(impl: ImageServiceImpl): ImageService
}

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    @Provides
    @Singleton
    fun provideImageAPIInstance(): ImageAPI {
        return Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("http://localhost")
            .build()
            .create(ImageAPI::class.java)
    }
}