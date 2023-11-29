package com.example.sharebook.exchanges_feature.presentation.request_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchanges_feature.domain.usecases.RequestDetailsUseCase
import com.example.sharebook.exchanges_feature.domain.usecases.UpdateStatusRequestUseCase
import com.example.sharebook.exchanges_feature.presentation.request_details.channel.UpdateRequestChannel
import com.example.sharebook.exchanges_feature.presentation.request_details.event.RequestDetailsEvent
import com.example.sharebook.exchanges_feature.presentation.request_details.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class RequestDetailsViewModel @Inject constructor(
    private val userStorageManagement: UserStorageManagement,
    private val requestDetailsUseCase: RequestDetailsUseCase,
    private val updateStatusRequestUseCase: UpdateStatusRequestUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private var requestId: String = ""
    private var updateRequestChannel = Channel<UpdateRequestChannel>()
    var updateRquestChannelState = updateRequestChannel.receiveAsFlow()
    var uiState by mutableStateOf(UiState())


    init {
        stateHandle.get<String>(Constants.REQUEST_PARAM_ID)?.let { requestParamId ->
            this.requestId = requestParamId
            val user = runBlocking {
                userStorageManagement.retry().first()
            }
            uiState = uiState.copy(userLogged = user)
            getRequestDetails(requestParamId)
        }
    }

    fun onEvent(event: RequestDetailsEvent) {
       when (event) {
           is RequestDetailsEvent.ListDetails -> { getRequestDetails(requestId) }
           is RequestDetailsEvent.UpdateStatusRequest -> { updateStatusRequest(event.status!!) }
       }
    }

    private fun getRequestDetails(requestId: String) {
        viewModelScope.launch {
            requestDetailsUseCase(requestId, uiState.userLogged?.name).collect { response ->
                uiState = when (response) {
                    is Resource.Success -> { uiState.copy(requestDetails = response.data) }
                    is Resource.Error -> { uiState.copy(isErrorDetails = response.message) }
                    is Resource.Loading -> { uiState.copy(isLoadingDetails = true) }
                    is Resource.Finnaly -> { uiState.copy(isLoadingDetails = false) }
                }
            }
        }
    }

    private fun updateStatusRequest(status: BookRequestStatus) {
        viewModelScope.launch {
            updateStatusRequestUseCase(
                uiState.requestDetails!!,
                status, uiState.userLogged!!.id
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        updateRequestChannel.send(UpdateRequestChannel.Success())
                        getRequestDetails(requestId)
                    }
                    is Resource.Error -> {
                        updateRequestChannel.send(UpdateRequestChannel.Error(response.message))
                    }
                    is Resource.Loading -> { uiState = uiState.copy(isLoadingUpdateRequest = true) }
                    is Resource.Finnaly -> { uiState = uiState.copy(isLoadingUpdateRequest = false) }
                }
            }
        }
    }
}