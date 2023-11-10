package com.example.sharebook.book_feature.presentation.external_book.event


sealed class SendRequestBookEvent {
    object SendRequest: SendRequestBookEvent()
}