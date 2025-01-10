package com.morovez.imagegallery.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.morovez.imagegallery.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    hasBackButton: Boolean = false,
    onBackClickListener: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.bodyLarge
            )
        },
        navigationIcon = {
            var isButtonEnable by remember { mutableStateOf(true) }
            if (hasBackButton) {
                IconButton(onClick = {
                    if (isButtonEnable) {
                        isButtonEnable = false
                        onBackClickListener?.invoke()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}