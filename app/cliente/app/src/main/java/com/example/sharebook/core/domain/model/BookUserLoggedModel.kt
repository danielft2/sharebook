package com.example.sharebook.core.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag

data class BookUserLoggedModel(
    val id: String,
    val genders: List<String>,
    val name: String,
    val authors: List<String>,
    val edition: Int,
    val coverUrl: String,
    val bookState: String,
    val preference: BookPreferenceTag,
)

fun BookUserLoggedModel.toBookSummary(): BookSummaryModel {
    return BookSummaryModel(
        genders = genders,
        name = name,
        bookState = bookState,
        preference = preference,
        coverUrl = coverUrl,
        edition = edition,
        authors = authors,

        userName = "Você",
        userProfilePhoto = "",
        secondaryText = "Dono(a) do Livro"
    )
}