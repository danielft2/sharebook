package com.example.sharebook.exchanges_feature.data.remote.model

import com.example.sharebook.core.domain.model.BookSummaryModel
import com.example.sharebook.core.utils.Functions
import com.google.gson.annotations.SerializedName

data class UserRequestModel(
    @SerializedName("bookId")
    val bookId: String,

    @SerializedName("capa")
    val capa: String,

    @SerializedName("edicao")
    val edicao: Int,

    @SerializedName("estado")
    val estado: BookStateModel,

    @SerializedName("genero")
    val genero: List<String>,

    @SerializedName("nome")
    val nome: String?,

    @SerializedName("perfil")
    val perfil: String,

    @SerializedName("podeBuscar")
    val podeBuscar: Boolean,

    @SerializedName("querReceber")
    val querReceber: Boolean,

    @SerializedName("titulo")
    val titulo: String
)

fun UserRequestModel.toBookSumaryExternalModel(): BookSummaryModel {
    return BookSummaryModel(
        name = titulo,
        edition = edicao,
        authors = "Daniel",
        bookState = estado.nome,
        preference = Functions.getPreference(podeBuscar),
        genders = Functions.getValuesFromList(genero),
        userProfilePhoto = perfil,
        userFalbackPhoto = nome,
        userName = nome!!,
        secondaryText = "Dono(a) do Livro",
        coverUrl = capa
    )
}

fun UserRequestModel.toBookYourSumaryModel(
    userName: String
): BookSummaryModel {
    return BookSummaryModel(
        name = titulo,
        edition = edicao,
        authors = "Daniel",
        bookState = estado.nome,
        preference = Functions.getPreference(podeBuscar),
        genders = Functions.getValuesFromList(genero),
        userProfilePhoto = perfil,
        userFalbackPhoto = userName,
        userName = userName,
        secondaryText = "Dono(a) do Livro",
        coverUrl = capa
    )
}