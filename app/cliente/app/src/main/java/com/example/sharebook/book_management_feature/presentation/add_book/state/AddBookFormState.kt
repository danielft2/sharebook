package com.example.sharebook.book_management_feature.presentation.add_book.state

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.core.utils.UiText

// Analisar como lidar com os campos comentados
// Analisar adição dos campos capa, imagem e sinopse
data class AddBookFormState (
    val nome: String = "",
    val nomeError: UiText = UiText.DynamicText(""),


    val genero: String = "",
    val generoError: UiText = UiText.DynamicText(""),

    val autor: String = "",
    val autorError: UiText = UiText.DynamicText(""),

    val edicao: String = "",
    val edicaoError: UiText = UiText.DynamicText(""),

    val idioma: String = "",
    val idiomaError: UiText = UiText.DynamicText(""),

    // estado do livro tem que ser enum
    val isbn: String = "",
    val isbnSearchIsLoading: Boolean = false,
    val isbnError: UiText = UiText.DynamicText(""),

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
        // estadoLivro = estadoLivro
        queroBuscar = preferenciaBuscar,
        queroReceber = preferenciaReceber

    )
}