package com.example.sharebook.auth_feature.data.remote.response

import com.example.sharebook.auth_feature.data.remote.model.UserLoginModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("returnData")
    val userLoginModel: UserLoginModel
)