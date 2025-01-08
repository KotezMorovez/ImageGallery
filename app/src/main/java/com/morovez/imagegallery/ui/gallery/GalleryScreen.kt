package com.morovez.imagegallery.ui.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.morovez.imagegallery.R
import com.morovez.imagegallery.ui.theme.Color
import com.morovez.imagegallery.ui.dto.ItemUI
import com.morovez.imagegallery.ui.composable.TopBar
import com.morovez.imagegallery.common.pxToDp

@Composable
internal fun GalleryScreen(
    onItemClickListener: ((Int) -> Unit)? = null
) {
    GalleryScreenContent(itemsList = listOf(
        ItemUI(
            id = 1, preview = painterResource(R.drawable.ic_launcher_background)
        ),
        ItemUI(
            id = 2, preview = painterResource(R.drawable.ic_launcher_background)
        ),
        ItemUI(
            id = 3, preview = painterResource(R.drawable.ic_launcher_background)
        ),
        ItemUI(
            id = 4, preview = painterResource(R.drawable.ic_launcher_background)
        ),
        ItemUI(
            id = 5, preview = painterResource(R.drawable.ic_launcher_background)
        ),
    ), onItemClickListener = { id ->
        onItemClickListener?.invoke(id)
    })
}

@Composable
@Preview(showBackground = true)
fun GalleryScreenContent(
    itemsList: List<ItemUI> = listOf(), // TODO: Add preview parameter
    onBackClickListener: (() -> Unit)? = null,
    onItemClickListener: ((Int) -> Unit)? = null
) {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Background, topBar = {
        TopBar(
            title = stringResource(id = R.string.gallery_title)
        )
    }) { padding ->
        val scaffoldPadding = PaddingValues(
            top = padding.calculateTopPadding() + 16.dp,
            bottom = padding.calculateBottomPadding() + 16.dp,
            start = padding.calculateStartPadding(LayoutDirection.Ltr) + 24.dp,
            end = padding.calculateEndPadding(LayoutDirection.Ltr) + 24.dp
        )
        val context = LocalContext.current
        val displayMetrics = context.resources.displayMetrics
        val width = pxToDp(displayMetrics.widthPixels.toFloat(), context)
        val columnsAmount = width / 100

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnsAmount),
            modifier = Modifier.padding(scaffoldPadding),
        ) {
            items(items = itemsList, key = { item ->
                item.id
            }, contentType = { null }, itemContent = { item ->
                ItemView(item = item, onItemClicked = {
                    onItemClickListener?.invoke(it)
                })
            })
        }
    }
}

@Composable
private fun ItemView(
    item: ItemUI, onItemClicked: (id: Int) -> Unit
) {
    Box(
        modifier = Modifier.padding(4.dp).clickable { onItemClicked.invoke(item.id) },
    ) {
        Image(
            painter = item.preview, contentDescription = "image"
        )
    }
}