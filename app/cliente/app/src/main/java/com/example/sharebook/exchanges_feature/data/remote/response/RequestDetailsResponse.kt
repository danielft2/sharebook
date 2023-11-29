package com.example.sharebook.exchanges_feature.data.remote.response

import com.example.sharebook.core.utils.Functions
import com.example.sharebook.exchanges_feature.data.remote.model.UserRequestModel
import com.example.sharebook.exchanges_feature.data.remote.model.toBookSumaryExternalModel
import com.example.sharebook.exchanges_feature.data.remote.model.toBookYourSumaryModel
import com.example.sharebook.exchanges_feature.domain.model.RequestDetailsModel
import com.google.gson.annotations.SerializedName

data class RequestDetailsResponse(
    @SerializedName("extertalUserRescueData")
    val extertalUserRequest: UserRequestModel,

    @SerializedName("userRescueData")
    val userLoggedRequest: UserRequestModel,

    @SerializedName("status")
    val status: String,

    @SerializedName("isRescueFromUserLogged")
    val isRescueFromUserLogged: Boolean
)

fun RequestDetailsResponse.toRequestDetailsModel(userNameLogged: String): RequestDetailsModel {
    return RequestDetailsModel(
        userLoggedBook = userLoggedRequest.toBookYourSumaryModel(userNameLogged),
        userExternalBook = extertalUserRequest.toBookSumaryExternalModel(),
        userExternalLocation = extertalUserRequest.localizacao ?: "",
        userExternalPhone = extertalUserRequest.telefone ?: "",
        status = Functions.getStatusByName(status),
        isRequestFromUserLogged = isRescueFromUserLogged
    )
}