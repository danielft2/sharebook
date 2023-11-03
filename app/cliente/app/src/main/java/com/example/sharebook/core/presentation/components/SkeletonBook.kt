package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.utils.skeleton

@Composable
fun SkeletonBook(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(modifier = Modifier
            .height(150.dp)
            .width(110.dp)
            .clip(Shapes.small)
            .skeleton()
        )
    }
}

