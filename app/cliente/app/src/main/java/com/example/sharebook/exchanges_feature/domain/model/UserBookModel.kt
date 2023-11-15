package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.exchanges_feature.domain.enum.BookState

data class UserBookModel(
    val id: String,
    val gender: String,
    val name: String,
    val author: String,
    val edtion: String,
    val coverUrl: String,
    val state: BookState,
    val preference: String,
)
