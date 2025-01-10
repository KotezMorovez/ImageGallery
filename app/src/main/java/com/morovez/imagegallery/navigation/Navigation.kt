package com.morovez.imagegallery.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.morovez.imagegallery.ui.gallery.GalleryScreen
import com.morovez.imagegallery.ui.view_image.ViewImageScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class ImageGalleryScreens {
    GALLERY,
    VIEW_IMAGE
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
                onItemClickListener = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(ImageGalleryScreens.VIEW_IMAGE.name + "/${encodedUrl}")
                }
            )
        }

        composable(
            route = ImageGalleryScreens.VIEW_IMAGE.name + "/{encodedUrl}",
            arguments = listOf(navArgument("encodedUrl") {
                type = NavType.StringType
            })
        ) {
            ViewImageScreen(
                url = it.arguments?.getString("encodedUrl") ?: "",
                onBackClickListener = {
                    navController.popBackStack()
                }
            )
        }
    }
}
