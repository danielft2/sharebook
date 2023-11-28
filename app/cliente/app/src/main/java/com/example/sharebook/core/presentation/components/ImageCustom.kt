package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.R

@Composable
fun ImageCustom(
    url: String,
    modifier: Modifier,
    content: @Composable () -> Unit?
) {
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            onLoading = { isLoading = true },
            onSuccess =
            {
                isLoading = false
                isError = false
            },
            onError =
            {
                isLoading = false
                isError = true
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        if (isLoading) {
            content()
        } else if (isError) {
            Box(
                modifier = modifier.then(Modifier.background(gray200)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_loader_error),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}