package com.morovez.imagegallery.data

import javax.inject.Inject

interface ImageService {
    suspend fun getImagesUrlList(): List<String>
}

class ImageServiceImpl @Inject constructor(
    private val api: ImageAPI
) : ImageService {
    override suspend fun getImagesUrlList(): List<String> {
        val response = api.downloadFile()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { responseBody ->
                val bodyString = responseBody.string()
                val urlList = bodyString.lines()
                return urlList
            }
        }
        return listOf()
    }
}

