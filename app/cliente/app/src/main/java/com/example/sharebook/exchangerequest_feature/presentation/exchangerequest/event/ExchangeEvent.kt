package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event

import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

sealed class ExchangeEvent(val bookModel: MyBookModel? = null) {
    class SelectedBook(bookModel: MyBookModel?): ExchangeEvent(bookModel)
    object ChangeCheckboxValue: ExchangeEvent()
    object SendRequest: ExchangeEvent()
}
