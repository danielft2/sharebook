package com.example.sharebook.exchanges_feature.presentation.enteredprocess.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.exchanges_feature.data.local.BooksDataInMemory
import com.example.sharebook.exchanges_feature.presentation.mybooks.components.BookItem

@Composable
fun EnteredProcess(onNavigate: (route: String) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(BooksDataInMemory.enteredProcessList) {
                    BookItem(book = it) { onNavigate(PrivateRoutes.BookProcess.route) }
                }
            }
        }
    }
}