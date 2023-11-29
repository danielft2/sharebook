package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.gray200

@Composable
fun DividerCustom(
    spaceTop: Dp = 0.dp,
    spaceBottom: Dp = 0.dp
) {
    Spacer(modifier = Modifier.height(spaceTop))
    Divider(
        color = gray200,
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
    Spacer(modifier = Modifier.height(spaceBottom))
}