package com.morovez.imagegallery.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.morovez.imagegallery.ui.gallery.GalleryScreen

enum class ImageGalleryScreens {
    GALLERY,
    IMAGE_DETAILS
}

@Composable
fun ImageGalleryRouter(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = ImageGalleryScreens.GALLERY.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = ImageGalleryScreens.GALLERY.name) {
            GalleryScreen(
                onItemClickListener = {
//                   navController.navigate(ImageGalleryScreens.IMAGE_DETAILS.name)
                }
            )
        }
    }
}
