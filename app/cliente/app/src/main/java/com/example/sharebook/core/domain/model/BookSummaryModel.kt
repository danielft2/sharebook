package com.example.sharebook.core.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag

data class BookSummaryModel(
    val id: String?,
    val gender: String,
    val name: String,
    val edition: String,
    val author: String,
    val coverUrl: String,
    val state: BookStateTag,
    val preference: BookPreferenceTag,

    val userName: String,
    val secondaryText: String
)
