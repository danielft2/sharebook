package com.example.sharebook.book_feature.presentation.details_book_external.event


sealed class SendRequestBookEvent {
    object SendRequest: SendRequestBookEvent()
}