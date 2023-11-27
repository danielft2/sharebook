package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.runtime.Composable
import com.example.sharebook.book_feature.domain.model.toBookBookSummaryModel
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.components.book.BookSummarySkeleton
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state.ExchangeState

@Composable
fun BookSummaryConditional(uiState: ExchangeState) {
    if (uiState.isLoadingBookDetails) {
        BookSummarySkeleton()
    } else if (uiState.bookRequestDetails != null) {
        BookSummary(book = uiState.bookRequestDetails.toBookBookSummaryModel())
    }
}