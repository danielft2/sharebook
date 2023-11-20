package com.example.sharebook.core.domain.model

data class BookDetailsModel (
    val userName: String?,
    val userLocation: String?,
    val gender: String,
    val name: String,
    val author: String,
    val edtion: String,
    val coverUrl: String,
    //val state: BookState,
    val preference: String,
)
