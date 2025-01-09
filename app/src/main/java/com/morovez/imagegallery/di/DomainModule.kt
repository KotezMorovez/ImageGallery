package com.morovez.imagegallery.di

import com.morovez.imagegallery.domain.ImageInteractor
import com.morovez.imagegallery.domain.ImageInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Reusable
    @Binds
    fun bindImageInteractor(impl: ImageInteractorImpl): ImageInteractor
}