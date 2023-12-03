package com.example.sharebook.book_feature.presentation.form_book.state

import com.example.sharebook.book_feature.data.remote.model.FormBookModel
import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.core.presentation.components.input.types.SelectItem
import com.example.sharebook.core.utils.UiText
import java.io.File

data class UiState(
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

    val estado: SelectItem = SelectItem("", "Selecione"),
    val estadoError: UiText = UiText.DynamicString(""),

    val sinopse: String = "",
    val sinopseError: UiText = UiText.DynamicString(""),

    val latitude: String = "",
    val latitudeError: UiText = UiText.DynamicString(""),

    val longitude: String = "",
    val longitudeError: UiText = UiText.DynamicString(""),

    val capa: File? = null,
    val capaError: UiText = UiText.DynamicString(""),

    val imagens: MutableList<File> = mutableListOf(),

    val isbn: String = "",
    val isbnError: UiText = UiText.DynamicString(""),

    val preferenciaBuscar: Boolean = false,
    val preferenciaReceber: Boolean = false,

    val isLoadingStateRequest: Boolean = false,
    val isErrorStateRequest: String? = null,
    val statesItens: List<SelectItem> = listOf(),

    val isLoadingFormRequest: Boolean = false,

    val userLogged: UserModel? = null
)

fun UiState.toFormBookModel(): FormBookModel {
    return FormBookModel(
        isbn = isbn,
        nome = nome,
        autor = autor,
        genero = genero,
        edicao = edicao,
        idioma = idioma,
        sinopse = sinopse,
        podeBuscar = preferenciaBuscar,
        querRecber = preferenciaReceber,
        usuarioId = userLogged?.id ?: "",
        estadoId = estado.value,
        longitude = longitude,
        latitude = latitude,
        cape = capa!!,
        images = imagens.toList()
    )
}