package com.example.sharebook.auth_feature.data.remote.response

import com.example.sharebook.core.domain.model.UserModel
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

    @SerializedName("telefone")
    val telefone: String,

    @SerializedName("access_token")
    val accessToken: String
)

fun RegisterResponse.toUserModel() : UserModel {
    return UserModel(
        phone = telefone,
        cep = cep,
        email = email,
        name = nome,
        id = id
    )
}