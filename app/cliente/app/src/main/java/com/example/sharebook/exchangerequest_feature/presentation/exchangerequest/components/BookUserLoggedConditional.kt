package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.runtime.Composable
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state.ExchangeState

@Composable
fun BookUserLoggedConditional(uiState: ExchangeState, onClick: () -> Unit) {
    if (uiState.bookSelected != null) {
        BookSelected(
            book = uiState.bookSelected,
            userLoggedName = uiState.userLogged!!.name,
            userLoggedPhoto = uiState.userLogged.photoUrl ?: "",
            onClick = { onClick() }
        )
    } else {
        ChooseBook(onClick = { onClick() })
    }
}