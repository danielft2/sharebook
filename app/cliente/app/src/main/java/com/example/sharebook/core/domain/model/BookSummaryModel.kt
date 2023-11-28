package com.example.sharebook.core.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag

data class BookSummaryModel(
    val name: String,
    val edition: Int,
    val authors: String,
    val genders: String,
    val coverUrl: String,
    val bookState: String,
    val preference: BookPreferenceTag,

    val userName: String,
    val userFalbackPhoto: String? = null,
    val userProfilePhoto: String?,
    val secondaryText: String
)
