package com.example.sharebook.book_feature.presentation.details_book_self.event

sealed class RequestBookEvent {
    object AcceptRequest: RequestBookEvent()
    object RefuseRequest: RequestBookEvent()
}
