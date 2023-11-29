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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.sharebook.core.presentation.components.FloatingButtonNewBook
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.exchanges_feature.presentation.request_details.event.RequestDetailsEvent
import com.example.sharebook.exchanges_feature.presentation.requests.RequestsViewModel
import com.example.sharebook.exchanges_feature.presentation.requests.event.RequestsEvent

@Composable
fun Requests(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    requestsViewModel: RequestsViewModel = hiltViewModel(),
    onNavigate: (route: String) -> Unit,
) {
    val uiState = requestsViewModel.uiState

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                requestsViewModel.onEvent(RequestsEvent.ListRequest)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(background)
            .fillMaxSize()) {
            StateWraper(
                onClickTryAgain = { requestsViewModel.onEvent(RequestsEvent.ListRequest) },
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
