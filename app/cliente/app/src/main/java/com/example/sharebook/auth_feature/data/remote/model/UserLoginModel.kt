package com.example.sharebook.auth_feature.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserLoginModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("cep")
    val cep: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String
)