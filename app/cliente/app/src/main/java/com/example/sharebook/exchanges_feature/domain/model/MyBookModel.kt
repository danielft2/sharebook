package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.model.BookSummaryModel

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

fun MyBookModel.toBookSummaryModel(
    userName: String,
    userProfile: String
): BookSummaryModel {
    return BookSummaryModel(
        name = name,
        authors = authors,
        edition = edition,
        preference = preference,
        bookState = bookState,
        coverUrl = cover,
        genders = genders,

        userName = "Você",
        userFalbackPhoto = userName ,
        userProfilePhoto = userProfile,
        secondaryText = "Dono(a) do Livro"
    )
}


