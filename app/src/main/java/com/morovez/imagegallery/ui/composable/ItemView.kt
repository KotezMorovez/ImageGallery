package com.morovez.imagegallery.ui.composable

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.morovez.imagegallery.R
import com.morovez.imagegallery.utils.throttleClickable
import com.morovez.imagegallery.utils.dpToPx

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemView(
    context: Context,
    requestManager: RequestManager,
    item: String,
    onItemClickListener: (id: String) -> Unit,
    urlErrorListener: (id: String) -> Unit
) {
    GlideImage(
        model = item,
        loading = placeholder(painterResource(R.drawable.ic_launcher_background)),
        failure = placeholder(painterResource(R.drawable.ic_launcher_foreground)),
        contentDescription = "image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .throttleClickable { onItemClickListener.invoke(item) }
    ) {
        it.thumbnail(
            requestManager
                .asDrawable()
                .load(item)
                .override(dpToPx(100f, context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        urlErrorListener.invoke(item)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        return true
                    }
                })
        )
    }
}