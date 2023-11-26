package com.example.sharebook.book_feature.data.remote.response

import com.example.sharebook.book_feature.data.remote.model.BookInformationsModel
import com.example.sharebook.book_feature.data.remote.model.UserInformationModel
import com.example.sharebook.book_feature.domain.model.BookDetailsModel
import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.utils.Functions

data class BookDetailsResponse(
    val book: BookInformationsModel,
    val userInformation: UserInformationModel
)

fun BookDetailsResponse.toBookDetailsModel(): BookDetailsModel {
    return BookDetailsModel(
        id = book.id,
        name = book.nome,
        edition = book.edicao,
        authors = Functions.getValuesFromList(book.autor),
        bookState = book.bookState,
        genders = Functions.getValuesFromList(book.genders),
        cover = book.capa,
        images = book.imagens,
        synopsis = book.sinopse,
        preference = Functions.getPreference(book.podeBuscar),

        usuarioId = book.usuarioId,
        userName = userInformation.userName,
        userLocation = userInformation.city + "- ${userInformation.uf}",
        userProfilePhoto = userInformation.profilePhoto ?: ""
    )
}
