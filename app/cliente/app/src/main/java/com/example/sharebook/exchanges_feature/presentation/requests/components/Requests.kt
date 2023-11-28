package com.example.sharebook.exchanges_feature.presentation.requests.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharebook.core.presentation.components.FloatingButtonNewBook
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.exchanges_feature.presentation.requests.RequestsViewModel

@Composable
fun Requests(
    requestsViewModel: RequestsViewModel = hiltViewModel(),
    onNavigate: (route: String) -> Unit
) {
    val uiState = requestsViewModel.uiState

    Surface(modifier = Modifier.fillMaxSize()) {
        FloatingButtonNewBook { }

        Column(modifier = Modifier
            .background(background)
            .fillMaxSize()) {
            StateWraper(
                onClickTryAgain = { requestsViewModel.listRequests(uiState.userLogged?.id ?: "") },
                isLoading = uiState.isLoading,
                isError = !uiState.isError.isNullOrEmpty()
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(background)) {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 100.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.requestsList) {
                            RequestItem(requestBook = it) {
                                onNavigate(PrivateRoutes.RequestDetails.withArgs(it.id))
                            }
                        }
                    }
                }
            }
        }
    }
}
