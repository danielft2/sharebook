package com.example.sharebook.book_feature.presentation.self_book.event

sealed class RequestBookEvent {
    object AcceptRequest: RequestBookEvent()
    object RefuseRequest: RequestBookEvent()
}
