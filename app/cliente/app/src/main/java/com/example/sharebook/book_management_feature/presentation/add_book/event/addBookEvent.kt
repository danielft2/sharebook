package com.example.sharebook.book_management_feature.presentation.add_book.event

sealed class AddBookEvent {
    object NavigateToSuccessScreen : AddBookEvent()
}