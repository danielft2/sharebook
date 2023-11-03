package com.example.sharebook.exchanges_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun Exchanges() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(background)) {
            Text("Suas Trocas", color = green900, textAlign = TextAlign.Center)
        }
    }
}