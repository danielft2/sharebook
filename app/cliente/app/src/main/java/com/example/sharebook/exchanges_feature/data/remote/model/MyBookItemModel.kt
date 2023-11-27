package com.example.sharebook.exchanges_feature.data.remote.model

import com.example.sharebook.core.utils.Functions
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

    @SerializedName("genero")
    val generos: List<String>,

    @SerializedName("estado")
    val estado: String,

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
        genders = Functions.getValuesFromList(generos),
        authors = Functions.getValuesFromList(autores),
        preference = Functions.getPreference(podeReceber),
        bookState = estado,
    )
}