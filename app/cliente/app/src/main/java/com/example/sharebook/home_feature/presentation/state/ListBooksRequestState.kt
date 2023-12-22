package com.example.sharebook.home_feature.presentation.state

data class ListBooksRequestState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = ""
)
