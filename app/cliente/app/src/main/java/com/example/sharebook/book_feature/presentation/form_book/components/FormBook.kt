package com.example.sharebook.book_feature.presentation.form_book.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sharebook.core.presentation.ui.theme.white

@Composable
fun FormBook() {
     Surface(modifier = Modifier.fillMaxSize()) {
         Column(modifier = Modifier
             .fillMaxSize()
             .background(white)
         ) {
             Form()
         }
     }
}