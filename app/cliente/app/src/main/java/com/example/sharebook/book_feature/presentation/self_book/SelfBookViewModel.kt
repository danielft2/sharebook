package com.example.sharebook.book_feature.presentation.self_book

import androidx.lifecycle.ViewModel
import com.example.sharebook.auth_feature.domain.usecase.LoginUseCase
import com.example.sharebook.auth_feature.presentation.login.event.LoginFormEvent
import com.example.sharebook.book_feature.domain.usecase.AcceptRequestUseCase
import com.example.sharebook.book_feature.domain.usecase.RefuseRequestUseCase
import com.example.sharebook.book_feature.presentation.self_book.event.RequestBookEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelfBookViewModel @Inject constructor(
    private val acceptRequestUseCase: AcceptRequestUseCase,
    private val refuseRequestUseCase: RefuseRequestUseCase
): ViewModel() {
    fun onEvent(event: RequestBookEvent) {
        when(event) {
            is RequestBookEvent.AcceptRequest -> {
                sendAcceptRequestQuery()
            }
            is RequestBookEvent.RefuseRequest -> {
                sendRefuseRequestQuery()
            }
        }
    }

    private fun sendAcceptRequestQuery() {
        acceptRequestUseCase()
    }

    private  fun sendRefuseRequestQuery() {
        refuseRequestUseCase()
    }
}