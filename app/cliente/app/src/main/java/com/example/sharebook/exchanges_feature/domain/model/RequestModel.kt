package com.example.sharebook.exchanges_feature.domain.model

data class RequestModel(
    val id: String,
    val title: String,
    val author: String,
    val bookImageURL: String,
    val edition: Int,
    val owner: String,
    val ownerProfileURL: String,
    val status: String,
)
