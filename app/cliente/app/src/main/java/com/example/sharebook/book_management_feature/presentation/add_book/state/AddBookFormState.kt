package com.example.sharebook.book_management_feature.presentation.add_book.state

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.core.utils.UiText

// Analisar adição dos campos capa, imagem e sinopse
data class AddBookFormState (
    val nome: String = "",
    val nomeError: UiText = UiText.DynamicString(""),

    val genero: String = "",
    val generoError: UiText = UiText.DynamicString(""),

    val autor: String = "",
    val autorError: UiText = UiText.DynamicString(""),

    val edicao: String = "",
    val edicaoError: UiText = UiText.DynamicString(""),

    val idioma: String = "",
    val idiomaError: UiText = UiText.DynamicString(""),

    val estadoLivro: String = "",
    val estadoLivroError: UiText = UiText.DynamicString(""),

    val sinopse: String = "",
    val sinopseError: UiText = UiText.DynamicString(""),

    val capaLivro: String = "",
    val capaLivroError: UiText = UiText.DynamicString(""),

    val imagemLivro: String = "",
    val imagemLivroError: UiText = UiText.DynamicString(""),

    val isbn: String = "",
    val isbnSearchIsLoading: Boolean = false,
    val isbnError: UiText = UiText.DynamicString(""),

    val preferenciaBuscar: Boolean = false,
    val preferenciaReceber: Boolean = false
)

fun AddBookFormState.toAddBookModel() : AddBookModel {
    return AddBookModel(
        isbn = isbn,
        nome = nome,
        genero = genero,
        autor = autor,
        edicao = edicao,
        idioma = idioma,
        estadoLivro = estadoLivro,
        queroBuscar = preferenciaBuscar,
        queroReceber = preferenciaReceber,
        capaLivro = capaLivro,
        imagemLivro = imagemLivro,
        sinopse = sinopse
    )
}