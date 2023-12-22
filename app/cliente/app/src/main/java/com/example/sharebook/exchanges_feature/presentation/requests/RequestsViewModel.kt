package com.example.sharebook.exchanges_feature.presentation.requests

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.domain.usecases.ListRequestsUseCase
import com.example.sharebook.exchanges_feature.presentation.requests.event.RequestsEvent
import com.example.sharebook.exchanges_feature.presentation.requests.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    private val listRequestsUseCase: ListRequestsUseCase,
    private val userStorageManagement: UserStorageManagement
) : ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
        val user = runBlocking { userStorageManagement.retry().first() }
        uiState = uiState.copy(userLogged = user)
        listRequests(user?.id ?: "")
    }

    fun onEvent(event: RequestsEvent) {
        when (event) {
            RequestsEvent.ListRequest -> {
                if (!uiState.isLoading) listRequests(uiState.userLogged?.id ?: "")
            }
        }
    }

    private fun listRequests(userId: String) {
        viewModelScope.launch {
            listRequestsUseCase(userId).collect { response ->
                uiState = when (response) {
                    is Resource.Success -> {
                        uiState.copy(requestsList = response.data ?: listOf())
                    }
                    is Resource.Error -> {
                        uiState.copy(isError = response.message)
                    }
                    is Resource.Loading -> {
                        uiState.copy(isLoading = true)
                    }
                    is Resource.Finnaly -> {
                        uiState.copy(isLoading = false)
                    }
                }
            }
        }
    }
}