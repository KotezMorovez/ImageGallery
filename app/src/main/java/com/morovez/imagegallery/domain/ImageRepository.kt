package com.morovez.imagegallery.domain

interface ImageRepository {
    suspend fun getImagesUrlList(): List<String>
}