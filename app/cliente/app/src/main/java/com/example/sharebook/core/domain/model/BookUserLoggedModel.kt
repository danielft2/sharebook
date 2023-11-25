package com.example.sharebook.core.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag

data class BookUserLoggedModel(
    val id: String,
    val gender: String,
    val name: String,
    val author: String,
    val edition: String,
    val coverUrl: String,
    val state: BookStateTag,
    val preference: BookPreferenceTag,
)

fun BookUserLoggedModel.toBookSummary(): BookSummaryModel {
    return BookSummaryModel(
        id = id,
        gender = gender,
        name = name,
        state = state,
        preference = preference,
        coverUrl = coverUrl,
        edition = edition,
        author = author,
        userName = "Você",
        secondaryText = "Dono(a) do Livro"
    )
}