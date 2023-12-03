package com.example.sharebook.book_feature.data.remote.model

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

data class FormBookModel(
    val isbn: String,
    val nome: String,
    val autor: String,
    val genero: String,
    val sinopse: String,
    val edicao: String,
    val idioma: String,
    val usuarioId: String,
    val estadoId: String,
    val querRecber: Boolean,
    val podeBuscar: Boolean,
    val latitude: String,
    val longitude: String,
    val cape: File,
    val images: List<File> = listOf()
)

fun FormBookModel.toFormBookMultipartform(): FormBookModelMultipartForm {
    return FormBookModelMultipartForm(
        isbn = MultipartBody.Part.createFormData("isbn", isbn),
        nome = MultipartBody.Part.createFormData("nome", nome),
        autor = MultipartBody.Part.createFormData("autor", autor),
        genero = MultipartBody.Part.createFormData("genero", genero),
        sinopse = MultipartBody.Part.createFormData("sinopse", sinopse),
        edicao = MultipartBody.Part.createFormData("edicao", edicao),
        idioma = MultipartBody.Part.createFormData("idioma", idioma),
        usuarioId = MultipartBody.Part.createFormData("usuario_id", usuarioId),
        estadoId = MultipartBody.Part.createFormData("estado_id", estadoId),
        querRecber = MultipartBody.Part.createFormData("quer_receber", querRecber.toString()),
        podeBuscar = MultipartBody.Part.createFormData("pode_buscar", podeBuscar.toString()),
        latitude = MultipartBody.Part.createFormData("latitude", latitude),
        longitude = MultipartBody.Part.createFormData("longitude", longitude),
        cape = MultipartBody.Part.createFormData("cape", cape.name, cape.asRequestBody()),
        images = images.map {
            MultipartBody.Part.createFormData("images", it.name, it.asRequestBody())
        },
    )
}