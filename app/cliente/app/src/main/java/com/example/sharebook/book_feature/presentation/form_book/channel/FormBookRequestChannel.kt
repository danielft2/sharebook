package com.example.sharebook.book_feature.presentation.form_book.channel

import com.example.sharebook.R

sealed class FormBookRequestChannel {
    data class Success(val message: Int): FormBookRequestChannel()
    data class Error(val message: String): FormBookRequestChannel()
}
