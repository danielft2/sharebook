package com.example.sharebook.book_management_feature.presentation.add_book.state

data class AddBookRequestState (
    val isLoading: Boolean = false,
    val sucess: Boolean = false,
    val error: String? = ""
)