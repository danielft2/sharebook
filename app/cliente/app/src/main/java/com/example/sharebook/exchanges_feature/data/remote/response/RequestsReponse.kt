package com.example.sharebook.exchanges_feature.data.remote.response

import com.example.sharebook.exchanges_feature.data.remote.model.RequestItemModel
import com.google.gson.annotations.SerializedName

data class RequestsReponse(
    @SerializedName("rescuesFromUser")
    val rescuesFromUser: List<RequestItemModel>,

    @SerializedName("rescuesToUser")
    val rescuesToUser: List<RequestItemModel>
)