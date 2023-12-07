package com.example.sharebook.core.presentation.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonAction(
    modifier: Modifier,
    enable: Boolean = true,
    resource: Int,
    sizeValue: Int = 24,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .size(40.dp)
            .then(modifier),
        enabled = enable
    ) {
        Image(
            painter = painterResource(resource),
            contentDescription = null,
            modifier = Modifier
                .size(sizeValue.dp)
                .padding(end = 1.dp, bottom = 1.dp)
        )
    }
}