package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state

import com.example.sharebook.book_feature.domain.model.BookDetailsModel
import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

data class ExchangeState(
    val checkbox: Boolean = false,
    val bookSelected: MyBookModel? = null,

    val requestBookId: String? = null,
    val requestSent: Boolean = false,
    val isLoadingRequest: Boolean = false,
    val isErrorRequest: String? = null,

    val bookRequestDetails: BookDetailsModel? = null,
    val isLoadingBookDetails: Boolean = false,
    val isErrorBookDetails: String? = null,

    val userLogged: UserModel? = null
)