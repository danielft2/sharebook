package com.example.sharebook.book_feature.presentation.external_book.state

import com.example.sharebook.book_feature.domain.model.BookDetailsModel

data class UiState(
    val isLoadingDetails: Boolean = false,
    val isErrorDetails: String? = null,
    val bookDetails: BookDetailsModel? = null
)
