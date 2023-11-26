package com.example.sharebook.exchanges_feature.presentation.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.exchanges_feature.presentation.mybooks.MyBooksViewModel

@Composable
fun MyBooks(
    myBooksViewModel: MyBooksViewModel = hiltViewModel(),
    onNavigate: (route: String) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        FloatingButtonNewBook { }

        StateWraper(
            onClickTryAgain = { myBooksViewModel.getListMyBooks() },
            isLoading = myBooksViewModel.uiState.isLoading,
            isError = !myBooksViewModel.uiState.isError.isNullOrEmpty()
        ) {
            Column(modifier = Modifier.fillMaxSize().background(background)) {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(myBooksViewModel.uiState.listMyBooks) {
                        BookItem(book = it) { onNavigate(PrivateRoutes.UserBook.route) }
                    }
                }
            }
        }
    }
}