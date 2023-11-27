package com.example.sharebook.book_feature.data.remote.model

import com.google.gson.annotations.SerializedName

data class BookInformationsModel(
    @SerializedName("autor")
    val autor: List<String>,

    @SerializedName("capa")
    val capa: String,

    @SerializedName("edicao")
    val edicao: Int,

    @SerializedName("genders")
    val genders: List<String>,

    @SerializedName("estado_id")
    val estadoId: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("idioma")
    val idioma: String,

    @SerializedName("imagens")
    val imagens: List<String>,

    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("book_state")
    val bookState: String,

    @SerializedName("pode_busca")
    val podeBuscar: Boolean,

    @SerializedName("quer_receber")
    val querReceber: Boolean,

    @SerializedName("sinopse")
    val sinopse: String,

    @SerializedName("usuario_id")
    val usuarioId: String
)