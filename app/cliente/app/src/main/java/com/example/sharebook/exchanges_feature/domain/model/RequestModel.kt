package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.core.domain.enum.BookRequestStatus

data class RequestModel(
    val id: String,
    val title: String,
    val author: String,
    val bookImageURL: String,
    val edition: Int,
    val genders: String,
    val owner: String,
    val ownerProfileURL: String,
    val status: BookRequestStatus,
)
