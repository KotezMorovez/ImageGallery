package com.morovez.imagegallery.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ImageAPI {
    @GET("https://it-link.ru/test/images.txt")
    suspend fun downloadFile(): Response<ResponseBody>
}