package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageCustom(url: String, modifier: Modifier) {
    var isLoading by remember { mutableStateOf(false) }
    Box() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError = { isLoading = false },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        //SkeletonBook(isLoading = isLoading)
    }
}