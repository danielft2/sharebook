package com.example.sharebook.exchanges_feature.presentation.mybooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.domain.usecases.ListMyBooksUseCase
import com.example.sharebook.exchanges_feature.presentation.mybooks.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBooksViewModel @Inject constructor(
    private val listMyBooksUseCase: ListMyBooksUseCase
) : ViewModel() {
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
}