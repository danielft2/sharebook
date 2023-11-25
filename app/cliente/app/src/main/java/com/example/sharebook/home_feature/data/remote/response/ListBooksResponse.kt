package com.example.sharebook.home_feature.data.remote.response

import com.example.sharebook.home_feature.data.remote.model.BookModel

data class ListBooksResponse(
    val availableBooks: List<BookModel>,
    val favoriteGenders: List<BookModel>,
    val nextToYou: List<BookModel>
)