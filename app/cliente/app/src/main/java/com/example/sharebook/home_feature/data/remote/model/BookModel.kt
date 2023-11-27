package com.example.sharebook.home_feature.data.remote.model

import com.example.sharebook.home_feature.domain.model.BookPreviewModel
import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("autores")
    val autores: List<String>,

    @SerializedName("capa")
    val capa: String,

    @SerializedName("edicao")
    val edicao: Int,

    @SerializedName("estado_id")
    val estado_id: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("nome")
    val nome: String,
)

fun BookModel.toBookPreviewModel(): BookPreviewModel {
    return BookPreviewModel(
        id = id,
        name = nome,
        author = autores[0],
        coverUrl = capa,
    )
}