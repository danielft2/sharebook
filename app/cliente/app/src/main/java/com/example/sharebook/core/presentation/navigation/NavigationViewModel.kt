package com.example.sharebook.core.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val userStorageManagement: UserStorageManagement) : ViewModel() {
    var isLogged by mutableStateOf(false)

    init {
        retryUser()
    }

    private fun retryUser() {
        val user = userStorageManagement.retry()
        viewModelScope.launch {
            user.collect {
                isLogged = it != null
            }
        }
    }
}