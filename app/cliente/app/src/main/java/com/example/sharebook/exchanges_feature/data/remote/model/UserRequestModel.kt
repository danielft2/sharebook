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

    @SerializedName("autor")
    val autor: List<String>,

    @SerializedName("estado")
    val estado: String,

    @SerializedName("genero")
    val genero: List<String>,

    @SerializedName("nome")
    val nome: String?,

    @SerializedName("telefone")
    val telefone: String?,

    @SerializedName("localizacao")
    val localizacao: String?,

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
        id = bookId,
        name = titulo,
        edition = edicao,
        authors = Functions.getValuesFromList(autor),
        bookState = estado,
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
        id = bookId,
        name = titulo,
        edition = edicao,
        authors = Functions.getValuesFromList(autor),
        bookState = estado,
        preference = Functions.getPreference(podeBuscar),
        genders = Functions.getValuesFromList(genero),
        userProfilePhoto = perfil,
        userFalbackPhoto = userName,
        userName = "Você",
        secondaryText = "Dono(a) do Livro",
        coverUrl = capa
    )
}