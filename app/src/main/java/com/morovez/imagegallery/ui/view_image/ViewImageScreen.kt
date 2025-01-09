package com.morovez.imagegallery.ui.view_image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.morovez.imagegallery.ui.composable.TopBar
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable

@Composable
fun ViewImageScreen(
    url: String?, onBackClickListener: () -> Unit
) {
    var isToolbarVisible by remember { mutableStateOf(true) }

    if (url != null) {
        ViewImageContent(url = url,
            isToolbarVisible = isToolbarVisible,
            onBackClickListener = { onBackClickListener.invoke() },
            onClickListener = { isToolbarVisible = !isToolbarVisible })
    } else {
        onBackClickListener.invoke()
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ViewImageContent(
    url: String,
    isToolbarVisible: Boolean = true,
    onBackClickListener: (() -> Unit)? = null,
    onClickListener: (() -> Unit)? = null
) {

    Box(modifier = Modifier.fillMaxSize()) {
        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .zoomable(rememberZoomableState())
                .clickable(
                    interactionSource = null,
                    indication = null
                ) {
                    onClickListener?.invoke()
                },
            model = url,
            contentDescription = "image",
            contentScale = ContentScale.Fit
        )


        if (isToolbarVisible) {
            TopBar(
                hasBackButton = true,
                onBackClickListener = { onBackClickListener?.invoke() }
            )
        }
    }
}