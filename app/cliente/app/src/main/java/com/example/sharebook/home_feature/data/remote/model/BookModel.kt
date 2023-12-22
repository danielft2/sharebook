package com.example.sharebook.home_feature.data.remote.model

import com.example.sharebook.core.utils.Functions
import com.example.sharebook.home_feature.domain.model.BookPreviewModel
import com.example.sharebook.maps_feature.domain.model.BookMarker
import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("autores")
    val autores: List<String>,

    @SerializedName("capa")
    val capa: String,

    @SerializedName("edicao")
    val edicao: Int,

    @SerializedName("id")
    val id: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("latitude")
    val latitude: String,

    @SerializedName("longitude")
    val longitude: String
)

fun BookModel.toBookPreviewModel(): BookPreviewModel {
    return BookPreviewModel(
        id = id,
        name = nome,
        author = Functions.getValuesFromList(autores),
        coverUrl = capa,
    )
}

fun BookModel.toBookMarker(): BookMarker {
    return BookMarker(
        id = id,
        title = nome,
        userName = Functions.getValuesFromList(autores),
        longitude = longitude.toDouble(),
        latitude = latitude.toDouble()
    )
}