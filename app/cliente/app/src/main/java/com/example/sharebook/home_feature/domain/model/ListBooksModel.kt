package com.example.sharebook.home_feature.domain.model

data class ListBooksModel(
    val availableBooks: List<BookPreviewModel>,
    val favoriteGenders: List<BookPreviewModel>,
    val nextToYou: List<BookPreviewModel>
)
