package com.example.sharebook.book_feature.domain.model

import com.example.sharebook.book_feature.data.remote.model.BookStateModel
import com.example.sharebook.core.domain.model.BookSummaryModel
import com.example.sharebook.core.utils.Functions

data class BookDetailsModel(
    val isbn: String,
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
    val language: String,
    val canSearch: Boolean,
    val canReceive: Boolean,
    val bookState: BookStateModel,

    val isRequest: Boolean
)

fun BookDetailsModel.toBookBookSummaryModel(): BookSummaryModel {
    return BookSummaryModel(
        name = name,
        authors = authors,
        edition = edition,
        preference = Functions.getPreference(canSearch),
        bookState = bookState.nome,
        coverUrl = cover,
        genders = genders,

        userName = userName,
        userProfilePhoto = userProfilePhoto,
        secondaryText = userLocation ?: ""
    )
}

fun BookDetailsModel.toBookBookYourSummaryModel(): BookSummaryModel {
    return BookSummaryModel(
        name = name,
        authors = authors,
        edition = edition,
        preference = Functions.getPreference(canSearch),
        bookState = bookState.nome,
        coverUrl = cover,
        genders = genders,

        userName = "",
        userProfilePhoto = "",
        secondaryText = ""
    )
}