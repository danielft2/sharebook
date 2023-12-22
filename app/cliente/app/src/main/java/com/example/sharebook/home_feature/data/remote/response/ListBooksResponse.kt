package com.example.sharebook.home_feature.data.remote.response

import com.example.sharebook.home_feature.data.remote.model.BookModel
import com.google.gson.annotations.SerializedName

data class ListBooksResponse(
    @SerializedName("availableBooks")
    val availableBooks: List<BookModel>,

    @SerializedName("favoriteGenders")
    val favoriteGenders: List<BookModel>,

    @SerializedName("nextToYou")
    val nextToYou: List<BookModel>
)