package com.example.sharebook.exchanges_feature.presentation.mybooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.domain.usecases.DeleteMyBookUseCase
import com.example.sharebook.exchanges_feature.domain.usecases.ListMyBooksUseCase
import com.example.sharebook.exchanges_feature.presentation.mybooks.channel.DeleteMyBookChannel
import com.example.sharebook.exchanges_feature.presentation.mybooks.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBooksViewModel @Inject constructor(
    private val listMyBooksUseCase: ListMyBooksUseCase,
    private val deleteMyBookUseCase: DeleteMyBookUseCase
) : ViewModel() {
    private val deleteBookRequestChannel = Channel<DeleteMyBookChannel>()
    val deleteBookRequestState = deleteBookRequestChannel.receiveAsFlow()

    var uiState by mutableStateOf(UiState())
        private set

    init {
        getListMyBooks()
    }

    fun getListMyBooks() {
        viewModelScope.launch {
            listMyBooksUseCase().collect { response ->
                uiState = when(response) {
                    is Resource.Success -> { uiState.copy(listMyBooks = response.data!!) }
                    is Resource.Error -> { uiState.copy(isError = response.message) }
                    is Resource.Loading -> { uiState.copy(isLoading = true) }
                    is Resource.Finnaly -> { uiState.copy(isLoading = false) }
                }
            }
        }
    }

    fun deleteMyBook(bookId: String) {
        viewModelScope.launch {
            deleteMyBookUseCase(bookId).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        getListMyBooks()
                        deleteBookRequestChannel.send(DeleteMyBookChannel.Success())
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(isErrorDeleteBook = response.message)
                        deleteBookRequestChannel.send(DeleteMyBookChannel.Error(response.message!!))
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoadingDeleteBook = true)
                    }
                    is Resource.Finnaly -> {
                        uiState = uiState.copy(isLoadingDeleteBook = false)
                    }
                }
            }
        }
    }
}