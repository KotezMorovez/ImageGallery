package com.morovez.imagegallery.domain

import javax.inject.Inject

interface ImageInteractor {
    suspend fun getImages(): List<String>
}

class ImageInteractorImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : ImageInteractor {
    override suspend fun getImages(): List<String> {
        return imageRepository.getImagesUrlList()
    }
}
