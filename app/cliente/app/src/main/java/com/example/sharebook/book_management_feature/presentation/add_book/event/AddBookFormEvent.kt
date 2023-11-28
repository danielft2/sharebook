package com.example.sharebook.book_management_feature.presentation.add_book.event

sealed class AddBookFormEvent {
    data class NomeChange(val nome: String): AddBookFormEvent()
    data class GeneroChange(val genero: String): AddBookFormEvent()
    data class AutorChange(val autor: String): AddBookFormEvent()
    data class EdicaoChange(val edicao: String): AddBookFormEvent()
    data class IdiomaChange(val idioma: String): AddBookFormEvent()
    data class EstadoLivroChange(val estadoLivroChange: String): AddBookFormEvent()
    data class IsbnChange(val isbn: String): AddBookFormEvent()
    data class BuscarChange(val preferencia: Boolean): AddBookFormEvent()
    data class ReceberChange(val preferencia: Boolean): AddBookFormEvent()
    data class CapaLivroChange(val capaLivro: String): AddBookFormEvent()
    data class SinopseLivroChange(val sinopse: String): AddBookFormEvent()
    data class ImagemLivroChange(val imagemLivro: String): AddBookFormEvent()

    object Submit: AddBookFormEvent()
}