package com.example.sharebook.auth_feature.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("cep")
    val cep: String,

    @SerializedName("cidade")
    val cidade: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("estado")
    val estado: String,

    @SerializedName("foto_perfil")
    val fotoPerfil: Any,

    @SerializedName("id")
    val id: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("phone")
    val phone: String
)