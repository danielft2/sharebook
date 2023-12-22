package com.example.sharebook.book_feature.presentation.form_book.state

import com.example.sharebook.book_feature.data.remote.model.CreateBookModel
import com.example.sharebook.book_feature.data.remote.model.UpdateBookModel
import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.core.presentation.components.input.types.SelectItem
import com.example.sharebook.core.utils.UiText
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
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

    val userLogged: UserModel? = null,
    val bookId: String? = null
)

fun UiState.toCreateBookModel(): CreateBookModel {
    return CreateBookModel(
        isbn = MultipartBody.Part.createFormData("isbn", isbn),
        nome = MultipartBody.Part.createFormData("nome", nome),
        autor = MultipartBody.Part.createFormData("autor", autor),
        genero = MultipartBody.Part.createFormData("genero", genero),
        sinopse = MultipartBody.Part.createFormData("sinopse", sinopse),
        edicao = MultipartBody.Part.createFormData("edicao", edicao),
        idioma = MultipartBody.Part.createFormData("idioma", idioma),
        usuarioId = MultipartBody.Part.createFormData("usuario_id", userLogged!!.id),
        estadoId = MultipartBody.Part.createFormData("estado_id", estado.value),
        querRecber = MultipartBody.Part.createFormData("quer_receber", preferenciaReceber.toString()),
        podeBuscar = MultipartBody.Part.createFormData("pode_buscar", preferenciaBuscar.toString()),
        latitude = MultipartBody.Part.createFormData("latitude", latitude),
        longitude = MultipartBody.Part.createFormData("longitude", longitude),
        cape = MultipartBody.Part.createFormData("cape", capa!!.name, capa.asRequestBody()),
        images = imagens.map {
            MultipartBody.Part.createFormData("images", it.name, it.asRequestBody())
        },
    )
}

fun UiState.toUpdateBookModel(): UpdateBookModel {
    var capaMultiPart: MultipartBody.Part? = null
    if (capa != null) {
        capaMultiPart = MultipartBody.Part.createFormData("cape", capa.name, capa.asRequestBody())
    }

    return UpdateBookModel(
        nome = MultipartBody.Part.createFormData("nome", nome),
        autor = MultipartBody.Part.createFormData("autor", autor),
        genero = MultipartBody.Part.createFormData("genero", genero),
        sinopse = MultipartBody.Part.createFormData("sinopse", sinopse),
        edicao = MultipartBody.Part.createFormData("edicao", edicao),
        idioma = MultipartBody.Part.createFormData("idioma", idioma),
        estadoId = MultipartBody.Part.createFormData("estado_id", estado.value),
        querRecber = MultipartBody.Part.createFormData("quer_receber", preferenciaReceber.toString()),
        podeBuscar = MultipartBody.Part.createFormData("pode_buscar", preferenciaBuscar.toString()),
        cape = capaMultiPart
    )
}