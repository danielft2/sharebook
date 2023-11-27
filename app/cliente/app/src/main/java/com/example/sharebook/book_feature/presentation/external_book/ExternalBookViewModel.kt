package com.example.sharebook.book_feature.presentation.external_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.book_feature.domain.usecase.DetailsBookUseCase
import com.example.sharebook.book_feature.presentation.external_book.state.UiState
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExternalBookViewModel @Inject constructor(
    private val detailsBookUseCase: DetailsBookUseCase,
    stateHandle: SavedStateHandle
): ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
        stateHandle.get<String>(Constants.BOOK_PARAM_ID)?.let { bookId ->
            getDetailsBook(bookId)
        }
    }

    private fun getDetailsBook(bookId: String) {
        viewModelScope.launch {
            detailsBookUseCase(bookId).collect {response ->
                when(response) {
                    is Resource.Success -> {
                        uiState = uiState.copy(bookDetails = response.data)
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(isErrorDetails = response.message)
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoadingDetails = true)
                    }
                    is Resource.Finnaly -> {
                        uiState = uiState.copy(isLoadingDetails = false)
                    }
                }
            }
        }
    }
}