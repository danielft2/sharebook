package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.white

@Composable
fun FloatingButtonNewBook(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 68.dp, end = 16.dp)
            .zIndex(100f),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            elevation = FloatingActionButtonDefaults.elevation(12.dp),
            backgroundColor = green500,
            contentColor = white
        ) {
            Icon(Icons.Filled.Add, null)
        }
    }
}