package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.state

import com.example.sharebook.core.domain.model.BookUserLoggedModel

data class ChosseBookState(
    val search: String = "",
    val bookListOriginal: List<BookUserLoggedModel> = listOf(),
    val bookListFilter: List<BookUserLoggedModel> = listOf()
)