package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.book_feature.domain.usecase.DetailsBookUseCase
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.domain.usecases.SendRequestUseCase
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.channel.SendRequestChannel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event.ExchangeEvent
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state.ExchangeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ExchangeRequestViewModel @Inject constructor(
    private val userStorageManagement: UserStorageManagement,
    private val detailsBookUseCase: DetailsBookUseCase,
    private val sendRequestUseCase: SendRequestUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(ExchangeState())
        private set

    private var requestChannel = Channel<SendRequestChannel>()
    var requestChannelState = requestChannel.receiveAsFlow()

    init {
        getUserLogged()
        stateHandle.get<String>(Constants.BOOK_PARAM_ID)?.let { bookId ->
            uiState = uiState.copy(requestBookId = bookId)
            getDetailsBook(bookId)
        }
    }

    private fun getUserLogged() {
        val user = runBlocking { userStorageManagement.retry().first() }
        uiState = uiState.copy(userLogged = user)
    }

    private fun getDetailsBook(bookId: String) {
        viewModelScope.launch {
            detailsBookUseCase(bookId).collect { response ->
                when (response) {
                    is Resource.Success -> { uiState = uiState.copy(bookRequestDetails = response.data) }
                    is Resource.Error -> { uiState = uiState.copy(isErrorBookDetails = response.message) }
                    is Resource.Loading -> { uiState = uiState.copy(isLoadingBookDetails = true) }
                    is Resource.Finnaly -> { uiState = uiState.copy(isLoadingBookDetails = false) }
                }
            }
        }
    }

    private fun sendRequest() {
        if (
            !uiState.requestBookId.isNullOrEmpty() &&
            uiState.bookSelected != null &&
            uiState.userLogged != null
        ) {
            val body = SendRequestModel(
                idBook = uiState.requestBookId!!,
                idBookFromRescue = uiState.bookSelected!!.id ?: "",
                idRescueUser = uiState.userLogged!!.id ?: "",
                status = BookRequestStatus.SEND
            )

            viewModelScope.launch {
                sendRequestUseCase(body).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            uiState = uiState.copy(isSuccessRequest = true)
                            requestChannel.send(SendRequestChannel.SuccessRequest())
                        }
                        is Resource.Error -> {
                            uiState = uiState.copy(isErrorRequest = response.message)
                            requestChannel.send(SendRequestChannel.ErrorRequest(response.message))
                        }
                        is Resource.Loading -> { uiState = uiState.copy(isLoadingRequest = true) }
                        is Resource.Finnaly -> { uiState = uiState.copy(isLoadingRequest = false) }
                    }
                }
            }
        }
    }

    fun event(exchangeEvent: ExchangeEvent) {
        when (exchangeEvent) {
            is ExchangeEvent.ChangeCheckboxValue -> {
                uiState = uiState.copy(checkbox = !uiState.checkbox)
            }
            is ExchangeEvent.SelectedBook -> {
                uiState = uiState.copy(bookSelected = exchangeEvent.bookModel)
            }
            is ExchangeEvent.SendRequest -> { sendRequest() }
        }
    }
}