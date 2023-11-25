package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event

import com.example.sharebook.core.domain.model.BookUserLoggedModel

sealed class ExchangeEvent(val bookModel: BookUserLoggedModel? = null) {
    class SelectedBook(bookModel: BookUserLoggedModel?): ExchangeEvent(bookModel)
    object ChangeCheckboxValue: ExchangeEvent()
}
