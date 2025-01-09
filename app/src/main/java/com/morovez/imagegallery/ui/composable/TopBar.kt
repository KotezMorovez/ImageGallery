package com.morovez.imagegallery.ui.composable

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morovez.imagegallery.ui.theme.Color
import com.morovez.imagegallery.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    hasBackButton: Boolean = false,
    hasActionButton: Boolean = false,
    onActionClickListener: (() -> Unit)? = null,
    onBackClickListener: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.bodyLarge
            )
        },
        actions = {
            if (hasActionButton) {
                IconButton(onClick = {
                    onActionClickListener?.invoke()
                }) {
                    Icon(
                        modifier = Modifier
                            .height(36.dp)
                            .aspectRatio(1f),
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = Color.PrimaryTextColor
                    )
                }
            }
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = {
                    onBackClickListener?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = Color.Background,
            scrolledContainerColor = Color.Background,
            navigationIconContentColor = Color.PrimaryTextColor,
            titleContentColor = Color.PrimaryTextColor,
            actionIconContentColor = Color.PrimaryTextColor
        )
    )
}