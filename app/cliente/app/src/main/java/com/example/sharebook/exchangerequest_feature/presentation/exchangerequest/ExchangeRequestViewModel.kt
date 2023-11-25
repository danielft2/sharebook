package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event.ExchangeEvent
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state.ExchangeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeRequestViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(ExchangeState())
        private set

    fun event(exchangeEvent: ExchangeEvent) {
        when (exchangeEvent) {
            is ExchangeEvent.ChangeCheckboxValue -> {
                state = state.copy(checkbox = !state.checkbox)
            }
            is ExchangeEvent.SelectedBook -> {
                println(exchangeEvent.bookModel)
                state = state.copy(bookSelected = exchangeEvent.bookModel)
            }
        }
    }
}