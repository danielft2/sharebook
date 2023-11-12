package com.example.sharebook.book_feature.data.remote.response

import com.example.sharebook.auth_feature.data.remote.model.UserLoginModel
import com.example.sharebook.auth_feature.data.remote.response.LoginResponse
import com.example.sharebook.book_feature.data.mock.model.PedidoTrocaMock
import com.example.sharebook.core.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class RequestBookResponse(
    @SerializedName("foto_perfil")
    val fotoPerfil: String,
)

fun RequestBookResponse.toUserModel(): PedidoTrocaMock {
    return PedidoTrocaMock(

    )
}