package com.morovez.imagegallery.ui.gallery

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.morovez.imagegallery.R
import com.morovez.imagegallery.ui.composable.*
import com.morovez.imagegallery.ui.common.pxToDp

@Composable
fun GalleryScreen(
    viewModel: GalleryScreenViewModel = hiltViewModel(),
    onItemClickListener: ((String) -> Unit)? = null
) {
    val urlList by viewModel.imagesList.collectAsState()

    if(viewModel.isImageListNotLoaded) {
        viewModel.getImages()
    }

    GalleryScreenContent(
        itemsList = urlList,
        onItemClickListener = { id ->
            onItemClickListener?.invoke(id)
        },
        urlErrorListener = {
            viewModel.deleteUrlFromList(it)
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun GalleryScreenContent(
    @PreviewParameter(ItemsListPreviewParameterProvider::class) itemsList: List<String> = listOf(),
    onItemClickListener: ((String) -> Unit)? = null,
    urlErrorListener: ((id: String) -> Unit)? = null
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(id = R.string.gallery_title)
            )
        }
    ) { padding ->
        val context = LocalContext.current

        LazyVerticalGrid(
            columns = GridCells.Fixed(calculateColumnsCount(context)),
            modifier = Modifier.padding(calculatePaddings(padding)),
        ) {
            items(
                items = itemsList,
                key = { it },
                contentType = { null },
                itemContent = { item ->
                    ItemView(
                        context = context,
                        requestManager = LocalContext.current.let { remember(it) { Glide.with(it) } },
                        item = item,
                        onItemClicked = { onItemClickListener?.invoke(it) },
                        urlErrorListener = { urlErrorListener?.invoke(item) }
                    )
                }
            )
        }
    }
}

private fun calculatePaddings(paddingValues: PaddingValues): PaddingValues {
    return PaddingValues(
        top = paddingValues.calculateTopPadding() + 16.dp,
        bottom = paddingValues.calculateBottomPadding() + 16.dp,
        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 24.dp,
        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 24.dp
    )
}

private fun calculateColumnsCount(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val width = pxToDp(displayMetrics.widthPixels.toFloat(), context)
    return width / 100
}

class ItemsListPreviewParameterProvider : PreviewParameterProvider<List<String>> {
    override val values =
        sequenceOf(
            listOf(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKMBWXDkh39EwFfxTgsvf-f-IuC_cMHDX1Sg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWmWDG5z0KEBbc-My7aGzu7vNdzyyVjsu4Vw",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKPLHQe7eDJwGkcSdhhgH6rwHsGQ7ccSvBog",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiAiBlU5Oi9hhA1OOExgc-RK0ZSlqoon9aAQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmOIDvtPWSLzribzOkcHNoBS_csNNr_ITK6Q",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1ap_Z70D4gjMxteYl6AU_RkajV-ZtHvt0AQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLsuyPZUIejeqbwJHNChiqeuUzJyfX1chj7w",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSljmIAENV5PNzvZxA9z3y5t_V1AiIVhGzWw",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmf84YLb5lqDzJ-fcz-qHJO6Q6Bll7RprXcg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPbrKAhzUA3a1EF0gSiESp8xSnBd5dqwtCzw",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMUUwSom7iJMnW8WEQZSMLlSPsf4PUmjJl0A",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwXWNNzDd6Y1AlD7tv8U1IY6GnTNufZUvLww",
            )
        )
}