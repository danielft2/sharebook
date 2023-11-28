package com.example.sharebook.book_management_feature.data.remote.model

// Falta analisar como lidar com o campo estadoLivro
data class AddBookModel(
    val isbn: String,
    val nome: String,
    val genero: String,
    val autor: String,
    val edicao: String,
    val idioma: String,
    val sinopse: String,
    val capaLivro: String,
    val imagemLivro: String,
    val estadoLivro: String,
    val queroBuscar: Boolean,
    val queroReceber: Boolean
)