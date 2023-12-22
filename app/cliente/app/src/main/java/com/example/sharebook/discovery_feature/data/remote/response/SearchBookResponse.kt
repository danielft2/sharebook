package com.example.sharebook.discovery_feature.data.remote.response

import com.example.sharebook.discovery_feature.data.remote.mock.model.BookSearchMock
import com.google.gson.annotations.SerializedName

data class SearchBookResponse(
    @SerializedName("foto_perfil")
    val fotoPerfil: String,
)

fun SearchBookResponse.toUserModel(): BookSearchMock {
    return BookSearchMock(
    )
}