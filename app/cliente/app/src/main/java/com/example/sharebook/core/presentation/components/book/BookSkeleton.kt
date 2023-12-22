package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.utils.skeleton

@Composable
fun BookCoverSkeleton(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .clip(Shapes.small)
        .skeleton()
        .then(modifier)
    )
}

