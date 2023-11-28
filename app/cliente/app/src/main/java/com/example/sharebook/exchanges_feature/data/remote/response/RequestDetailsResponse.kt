package com.example.sharebook.exchanges_feature.data.remote.response

import com.example.sharebook.exchanges_feature.data.remote.model.UserRequestModel
import com.google.gson.annotations.SerializedName

data class RequestDetailsResponse(
    @SerializedName("extertalUserRescueData")
    val extertalUserRequest: UserRequestModel,

    @SerializedName("userRescueData")
    val userLoggedRequest: UserRequestModel
)