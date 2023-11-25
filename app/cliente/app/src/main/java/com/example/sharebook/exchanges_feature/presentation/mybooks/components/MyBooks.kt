package com.example.sharebook.exchanges_feature.presentation.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.exchanges_feature.data.local.booksInMemory

@Composable
fun MyBooks(onNavigate: (route: String) -> Unit) {
    val booksList = booksInMemory()
    Surface(modifier = Modifier.fillMaxSize()) {
        FloatingButtonNewBook { }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(booksList) {
                    BookItem(book = it) { onNavigate(PrivateRoutes.UserBook.route) }
                }
            }
        }
    }
}