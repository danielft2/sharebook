package com.example.sharebook.core.presentation.components.statewrapper

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.components.error.SomethingWrong
import com.example.sharebook.core.presentation.ui.theme.green600

@Composable
fun StateWraper(
    isLoading: Boolean = true,
    isError: Boolean = false,
    onClickTryAgain: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = (-50).dp),
            contentAlignment = Alignment.Center)
        {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                strokeWidth = 3.8.dp,
                color = green600
            )
        }
    } else if (isError) {
        SomethingWrong(onClickTryAgain = onClickTryAgain)
    } else {
        content()
    }
}