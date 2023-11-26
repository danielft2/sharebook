package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag

data class MyBookModel(
    val id: String,
    val authors: String,
    val cover: String,
    val name: String,
    val genders: String,
    val bookState: String,
    val preference: BookPreferenceTag,
    val edition: Int
)
