package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.event.ChooseBookEvent
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.state.ChosseBookState
import com.example.sharebook.exchanges_feature.domain.usecases.ListMyBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChooseBookViewModel @Inject constructor(
    private val listMyBooksUseCase: ListMyBooksUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ChosseBookState())
    private set

    init {
        if (uiState.bookListOriginal.isEmpty()) getListMyBooks()
    }

    fun event(chooseBookEvent: ChooseBookEvent) {
        when (chooseBookEvent) {
            is ChooseBookEvent.SearchChange -> {
                uiState = uiState.copy(
                    search = chooseBookEvent.term,
                    bookListFilter = uiState.bookListOriginal.filter {
                        it.name.lowercase(Locale.ROOT)
                            .contains(chooseBookEvent.term.lowercase(Locale.ROOT))
                    }
                )
            }
        }
    }

    fun getListMyBooks() {
        viewModelScope.launch {
            listMyBooksUseCase().collect { response ->
                uiState = when(response) {
                    is Resource.Success -> {
                        uiState.copy(
                            bookListOriginal = response.data!!,
                            bookListFilter = response.data
                        )
                    }
                    is Resource.Error -> { uiState.copy(isError = response.message) }
                    is Resource.Loading -> { uiState.copy(isLoading = true) }
                    is Resource.Finnaly -> { uiState.copy(isLoading = false) }
                }
            }
        }
    }
}