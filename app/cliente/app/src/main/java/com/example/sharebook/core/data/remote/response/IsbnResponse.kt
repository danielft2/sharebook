package com.example.sharebook.core.data.remote.response

import com.example.sharebook.core.domain.model.IsbnModel
import com.google.gson.annotations.SerializedName

data class IsbnResponse (
    @SerializedName("totalItems")
    val totalItens: Int,

    @SerializedName("items")
    val itens: List<BookItem>
)

data class BookItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @SerializedName("title")
    val title: String,

    @SerializedName("authors")
    val authors: List<String>,

    @SerializedName("description")
    val sinopse: String,

    @SerializedName("categories")
    val generos: List<String>,

    @SerializedName("language")
    val idioma: String
)

fun IsbnResponse.toIsbnModel(): IsbnModel {
    return IsbnModel(
        autor = itens[0].volumeInfo.authors[0],
        genero = itens[0].volumeInfo.generos[0],
        idioma = itens[0].volumeInfo.idioma,
        nome = itens[0].volumeInfo.title
    )
}