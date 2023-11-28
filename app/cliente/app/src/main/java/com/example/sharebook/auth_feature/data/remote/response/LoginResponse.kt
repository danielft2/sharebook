package com.example.sharebook.auth_feature.data.remote.response

import com.example.sharebook.auth_feature.data.remote.model.UserLoginModel
import com.example.sharebook.core.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("returnData")
    val userLoginModel: UserLoginModel
)

fun LoginResponse.toUserModel(): UserModel {
    return UserModel(
        id = userLoginModel.id,
        name = userLoginModel.name,
        email = userLoginModel.email,
        phone = userLoginModel.phone,
        cep = userLoginModel.cep,
        photoUrl = ""
    )
}