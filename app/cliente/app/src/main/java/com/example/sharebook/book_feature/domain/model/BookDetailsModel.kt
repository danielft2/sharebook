package com.example.sharebook.book_feature.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.model.BookSummaryModel

data class BookDetailsModel(
    val id: String,
    val usuarioId: String,
    val userName: String,
    val userLocation: String?,
    val userProfilePhoto: String?,

    val name: String,
    val authors: String,
    val genders: String,
    val cover: String,
    val edition: Int,
    val images: List<String>,
    val synopsis: String,
    val preference: BookPreferenceTag,
    val bookState: String
)

fun BookDetailsModel.toBookBookSummaryModel(): BookSummaryModel {
    return BookSummaryModel(
        name = name,
        authors = authors,
        edition = edition,
        preference = preference,
        bookState = bookState,
        coverUrl = cover,
        genders = genders,

        userName = userName,
        userProfilePhoto = userProfilePhoto,
        secondaryText = userLocation ?: ""
    )
}
