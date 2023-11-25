package com.example.sharebook.exchanges_feature.domain.model
import com.example.sharebook.core.domain.enum.BookStateTag

data class UserBookModel(
    val id: String,
    val gender: String,
    val name: String,
    val author: String,
    val edtion: String,
    val coverUrl: String,
    val state: BookStateTag,
    val preference: String,
)
