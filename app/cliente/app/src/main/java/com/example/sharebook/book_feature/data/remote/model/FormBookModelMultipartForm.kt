package com.example.sharebook.book_feature.data.remote.model

import okhttp3.MultipartBody
import java.io.File

data class FormBookModelMultipartForm(
    val isbn: MultipartBody.Part,
    val nome: MultipartBody.Part,
    val autor: MultipartBody.Part,
    val genero: MultipartBody.Part,
    val sinopse: MultipartBody.Part,
    val edicao: MultipartBody.Part,
    val idioma: MultipartBody.Part,
    val usuarioId: MultipartBody.Part,
    val estadoId: MultipartBody.Part,
    val querRecber: MultipartBody.Part,
    val podeBuscar: MultipartBody.Part,
    val latitude: MultipartBody.Part,
    val longitude: MultipartBody.Part,
    val cape: MultipartBody.Part,
    val images: List<MultipartBody.Part>
)
