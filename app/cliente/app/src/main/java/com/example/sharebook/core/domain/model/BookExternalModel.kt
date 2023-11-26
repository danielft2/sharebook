package com.example.sharebook.core.domain.model
import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookStateTag

data class BookExternalModel (
    val id: String,
    val genders: List<String>,
    val name: String,
    val author: List<String>,
    val edition: Int,
    val coverUrl: String,
    val bookState: String,
    val preference: BookPreferenceTag,

    val userName: String,
    val userLocation: String,
    val userProfileUrl: String? = null
)

fun BookExternalModel.toBookSummary(): BookSummaryModel {
    return BookSummaryModel(
        genders = genders,
        name = name,
        bookState = bookState,
        preference = preference,
        coverUrl = coverUrl,
        edition = edition,
        authors = author,
        userName = userName,

        userProfilePhoto = userProfileUrl,
        secondaryText = "Dono(a) do Livro"
    )
}