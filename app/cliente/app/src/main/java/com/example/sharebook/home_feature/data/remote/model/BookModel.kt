package com.example.sharebook.home_feature.data.remote.model

import com.example.sharebook.home_feature.domain.model.BookPreviewModel

data class BookModel(
    val autor: List<String>,
    val capa: String,
    val edicao: Int,
    val estado_id: String,
    val id: String,
    val idioma: String,
    val imagens: List<String>,
    val isbn: String,
    val nome: String,
    val pode_buscar: Boolean,
    val quer_receber: Boolean,
    val sinopse: String,
    val usuario_id: String
)

fun BookModel.toBookPreviewModel(): BookPreviewModel {
    return BookPreviewModel(
        id = id,
        name = nome,
        author = autor[0],
        coverUrl = capa,
    )
}