package com.example.sharebook.book_feature.data.remote.model

import com.example.sharebook.core.presentation.components.input.types.SelectItem

data class BookStateModel(
    val id: String,
    val nome: String
)

fun BookStateModel.toSelectItem(): SelectItem {
    return SelectItem(
        value = id,
        label = nome
    )
}