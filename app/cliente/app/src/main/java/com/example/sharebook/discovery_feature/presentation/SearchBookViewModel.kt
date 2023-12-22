package com.example.sharebook.discovery_feature.presentation

import androidx.lifecycle.ViewModel
import com.example.sharebook.discovery_feature.domain.usecase.SearchBookUsecase
import com.example.sharebook.discovery_feature.presentation.event.SearchBookEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(
    private val searchBookUsecase: SearchBookUsecase
): ViewModel() {
    //var uiState by mutableStateOf(HomeState())
    fun onEvent(event: SearchBookEvent) {
        when(event) {
            is SearchBookEvent.SearchBook -> {
                searchBook()
            }
        }
    }

    private fun searchBook() {
        searchBookUsecase()
    }
}