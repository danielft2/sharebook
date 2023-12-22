package com.example.sharebook.book_feature.presentation.form_book.event

import com.example.sharebook.core.presentation.components.input.types.SelectItem
import java.io.File

sealed class FormBookEvent() {
    data class NomeChange(val nome: String): FormBookEvent()
    data class GeneroChange(val genero: String): FormBookEvent()
    data class AutorChange(val autor: String): FormBookEvent()
    data class EdicaoChange(val edicao: String): FormBookEvent()
    data class IdiomaChange(val idioma: String): FormBookEvent()
    data class EstadoChange(val estadoLivro: SelectItem): FormBookEvent()
    data class IsbnChange(val isbn: String): FormBookEvent()
    data class BuscarChange(val preferencia: Boolean): FormBookEvent()
    data class ReceberChange(val preferencia: Boolean): FormBookEvent()
    data class CapaChange(val capaLivro: File): FormBookEvent()
    data class SinopseChange(val sinopse: String): FormBookEvent()
    data class LatitudeChange(val latitude: String): FormBookEvent()
    data class LongitudeChange(val longitude: String): FormBookEvent()
    data class ImagensChange(
        val index: Int,
        val imagemLivro: File,
        val isAdd: Boolean
    ): FormBookEvent()

    object Submit: FormBookEvent()
}
