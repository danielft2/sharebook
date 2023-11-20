package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.exchanges_feature.domain.enum.BookProcessState

data class EnteredProcessBookModel(
    val id: String,
    val gender: String,
    val name: String,
    val author: String,
    val edtion: String,
    val coverUrl: String,
    val state: BookProcessState,
    val preference: String,
    val userName: String,
    val userLocation: String
)
