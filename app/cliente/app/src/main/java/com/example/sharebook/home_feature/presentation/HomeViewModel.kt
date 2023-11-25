package com.example.sharebook.home_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.home_feature.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userStorageManagement: UserStorageManagement
    ): ViewModel() {
    var uiState by mutableStateOf(HomeState())

    init {
        retryUser()
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
}
