package com.morovez.imagegallery.data

import com.morovez.imagegallery.domain.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageService: ImageService
): ImageRepository {
    override suspend fun getImagesUrlList(): List<String> {
        return imageService.getImagesUrlList()
    }
}