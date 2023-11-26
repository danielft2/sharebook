package com.example.sharebook.book_feature.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserInformationModel(
    @SerializedName("city")
    val city: String,

    @SerializedName("uf")
    val uf: String,

    @SerializedName("profilePhoto")
    val profilePhoto: String?,

    @SerializedName("userName")
    val userName: String
)