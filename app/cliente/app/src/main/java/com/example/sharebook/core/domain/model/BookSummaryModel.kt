package com.example.sharebook.core.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag

data class BookSummaryModel(
    val name: String,
    val edition: Int,
    val authors: List<String>,
    val genders: List<String>,
    val coverUrl: String,
    val bookState: String,
    val preference: BookPreferenceTag,

    val userName: String,
    val userProfilePhoto: String?,
    val secondaryText: String
)
