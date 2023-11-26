package com.example.sharebook.exchanges_feature.presentation.mybooks.state

import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

data class UiState(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val listMyBooks: List<MyBookModel> = listOf()
)
