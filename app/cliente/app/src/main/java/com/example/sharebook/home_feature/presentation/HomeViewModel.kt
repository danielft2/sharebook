package com.example.sharebook.home_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.home_feature.domain.usecases.ListBooksUseCase
import com.example.sharebook.home_feature.presentation.state.ListBooksRequestState
import com.example.sharebook.home_feature.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userStorageManagement: UserStorageManagement,
    private val listBooksUseCase: ListBooksUseCase
    ): ViewModel() {

    private val logoutChannel = Channel<Boolean>()
    val logoutChannelState = logoutChannel.receiveAsFlow()

    var uiState by mutableStateOf(UiState())
        private set

    var listBooksRequestState by mutableStateOf(ListBooksRequestState())
        private set

    init {
        retryUser()

        if (
            uiState.nextToYou.isEmpty() &&
            uiState.availableBooks.isEmpty()
        ) { listBooks() }
    }

    private fun retryUser() {
        val user = userStorageManagement.retry()
        viewModelScope.launch {
            user.collect {
                if (it != null) {
                    uiState = uiState.copy(user = it)
                }
            }
        }
    }

    fun logout() {
        runBlocking {
            userStorageManagement.delete()
            logoutChannel.send(true)
        }
    }

    fun listBooks() {
        viewModelScope.launch {
            listBooksUseCase().collect {response ->
                when (response) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            availableBooks = response.data!!.availableBooks,
                            favoriteGenders = response.data.favoriteGenders,
                            nextToYou = response.data.nextToYou
                        )
                    }
                    is Resource.Error -> {
                        listBooksRequestState = listBooksRequestState.copy(error = response.message)
                    }
                    is Resource.Loading -> {
                        listBooksRequestState = listBooksRequestState.copy(isLoading = true)
                    }
                    is Resource.Finnaly -> {
                        listBooksRequestState = listBooksRequestState.copy(isLoading = false)
                    }
                }
            }
        }
    }
}
