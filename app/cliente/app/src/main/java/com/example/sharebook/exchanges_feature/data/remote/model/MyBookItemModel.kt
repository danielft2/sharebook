package com.example.sharebook.exchanges_feature.data.remote.model

import com.example.sharebook.core.utils.Functions
import com.example.sharebook.exchanges_feature.data.remote.responses.MyBooksResponse
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel
import com.google.gson.annotations.SerializedName

data class MyBookItemModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("autores")
    val autores: List<String>,

    @SerializedName("capa")
    val capa: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("genders")
    val genders: List<String>,

    @SerializedName("book_state")
    val bookState: String,

    @SerializedName("edicao")
    val edicao: Int,

    @SerializedName("pode_receber")
    val podeReceber: Boolean,

    @SerializedName("pode_buscar")
    val podeBuscar: Boolean
)

fun MyBookItemModel.toMyBookModel(): MyBookModel {
    return MyBookModel(
        id = id,
        name = nome,
        cover = capa,
        edition = edicao,
        genders = "",
        authors = Functions.getValuesFromList(autores),
        preference = Functions.getPreference(false),
        bookState = "Novo",
    )
}