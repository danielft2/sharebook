package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharebook.R

@Composable
fun Logo(type: LogoType) {
    val resource = if (type == LogoType.green) R.drawable.logo else R.drawable.logo_light

    Image(
        painter = painterResource(resource),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .width(100.dp)
            .height(20.dp)
    )
}

enum class LogoType {
    light,
    green
}