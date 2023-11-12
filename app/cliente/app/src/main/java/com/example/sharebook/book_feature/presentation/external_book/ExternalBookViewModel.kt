package com.example.sharebook.book_feature.presentation.external_book

import androidx.lifecycle.ViewModel
import com.example.sharebook.book_feature.domain.usecase.SendRequestUseCase
import com.example.sharebook.book_feature.presentation.external_book.event.SendRequestBookEvent
import com.example.sharebook.book_feature.presentation.self_book.event.RequestBookEvent
import javax.inject.Inject

class ExternalBookViewModel @Inject constructor(
    private val sendRequestUseCase: SendRequestUseCase
): ViewModel() {
    fun onEvent(event: SendRequestBookEvent) {
        when(event) {
            is SendRequestBookEvent.SendRequest -> {
                sendRequestQuery()
            }
        }
    }

    private  fun sendRequestQuery() {
        sendRequestUseCase()
    }
}