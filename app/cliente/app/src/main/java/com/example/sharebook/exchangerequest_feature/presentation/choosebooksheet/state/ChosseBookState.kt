package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.state

import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

data class ChosseBookState(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val search: String = "",
    val bookListOriginal: List<MyBookModel> = listOf(),
    val bookListFilter: List<MyBookModel> = listOf()
)