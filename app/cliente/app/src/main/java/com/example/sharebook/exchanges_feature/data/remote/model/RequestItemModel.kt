package com.example.sharebook.exchanges_feature.data.remote.model

import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.exchanges_feature.domain.model.RequestModel
import com.google.gson.annotations.SerializedName

data class RequestItemModel(
    @SerializedName("author")
    val author: List<String>,

    @SerializedName("bookImageURL")
    val bookImageURL: String,

    @SerializedName("edition")
    val edition: Int,

    @SerializedName("genero")
    val genero: List<String>,

    @SerializedName("id")
    val id: String,

    @SerializedName("owner")
    val owner: String,

    @SerializedName("ownerProfileURL")
    val ownerProfileURL: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("title")
    val title: String
)

fun RequestItemModel.toRequestModel(): RequestModel {
    return RequestModel(
        id = id,
        title = title,
        edition = edition,
        genders = Functions.getValuesFromList(genero),
        owner = owner,
        ownerProfileURL = ownerProfileURL,
        author = Functions.getValuesFromList(author),
        status = getStatusByName(status),
        bookImageURL = bookImageURL
    )
}

fun getStatusByName(name: String): BookRequestStatus {
    return if (name == BookRequestStatus.SEND.tag) BookRequestStatus.SEND
    else if (name == BookRequestStatus.FINALIZE.tag) BookRequestStatus.FINALIZE
    else if (name == BookRequestStatus.CANCEL.tag) BookRequestStatus.CANCEL
    else BookRequestStatus.ANONYMUS
}
