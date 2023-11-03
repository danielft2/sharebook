package com.example.sharebook.core.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.skeleton(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseIn)
        )
    )

    background(
        brush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFDDE6ED),
                Color(0xFFC1CCD5),
                Color(0xFFDDE6ED),
            ),
            startX = startOffsetX,
            endX = startOffsetX + size.width.toFloat()
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}