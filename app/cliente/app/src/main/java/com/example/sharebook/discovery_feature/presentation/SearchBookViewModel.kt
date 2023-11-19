package com.example.sharebook.discovery_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sharebook.discovery_feature.domain.usecase.SearchBookUsecase
import com.example.sharebook.discovery_feature.presentation.event.SearchBookEvent
import com.example.sharebook.home_feature.presentation.state.HomeState
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