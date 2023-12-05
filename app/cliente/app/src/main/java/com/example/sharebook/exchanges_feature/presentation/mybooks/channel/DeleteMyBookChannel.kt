package com.example.sharebook.exchanges_feature.presentation.mybooks.channel

import com.example.sharebook.R

sealed class DeleteMyBookChannel{
    data class Success(val message: Int = R.string.exchange_delete_my_book_success): DeleteMyBookChannel()
    data class Error(val message: String): DeleteMyBookChannel()
}

