package com.example.sharebook.home_feature.presentation.state

import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.home_feature.domain.model.BookPreviewModel

data class UiState(
    val user: UserModel? = null,
    val availableBooks: List<BookPreviewModel> = listOf(),
    val favoriteGenders: List<BookPreviewModel> = listOf(),
    val nextToYou: List<BookPreviewModel> = listOf()
)
