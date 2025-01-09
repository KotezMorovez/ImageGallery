package com.morovez.imagegallery.ui.gallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morovez.imagegallery.domain.ImageInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    private val imageInteractor: ImageInteractor
): ViewModel() {
    private val _imagesList: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val imagesList = _imagesList.asStateFlow()

    var isImageListNotLoaded = true

    fun getImages(){
        isImageListNotLoaded = false
        Log.e("DEBUG", "list")
        viewModelScope.launch {
            _imagesList.value = imageInteractor.getImages()
        }
    }

    fun deleteUrlFromList(url: String){
        _imagesList.value = _imagesList.value.filter { it != url }
    }
}