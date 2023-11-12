package com.example.sharebook.book_management_feature.data.remote.response

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.google.gson.annotations.SerializedName

data class AddBookResponse (
    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("genero_livro")
    val genero: String,

    @SerializedName("autor")
    val autor: String,

    @SerializedName("edicao")
    val edicao: String,

    @SerializedName("idioma")
    val idioma: String,

    @SerializedName("estado")
    val estadoLivro: String,

    @SerializedName("pode_buscar")
    val queroBuscar: Boolean,

    @SerializedName("quer_receber")
    val queroReceber: Boolean,

    /*@SerializedName("capa")
    val capaLivro: String,

    @SerializedName("imagens")
    val imagensLivro: List<String>,

    @SerializedName("sinopse")
    val sinopse: String*/
)
// Falta adicionar campos comentados
fun AddBookResponse.toAddBookModel() : AddBookModel {
    return AddBookModel(
        isbn = isbn,
        nome = nome,
        genero = genero,
        autor = autor,
        edicao = edicao,
        idioma = idioma,
        queroReceber = queroReceber,
        queroBuscar = queroBuscar
    )
}