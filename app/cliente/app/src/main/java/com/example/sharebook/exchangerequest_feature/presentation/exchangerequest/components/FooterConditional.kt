package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.green600
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state.ExchangeState

@Composable
fun FooterConditional(
    uiState: ExchangeState,
    onButtonClick: () -> Unit,
    onAcceptTermChange: () -> Unit
) {
    if (uiState.requestSent) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd)
        {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(green600)
                .padding(horizontal = 16.dp, vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        text = "Solicitação já realizada, acesse suas trocas.",
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = white
                    )
                }
            }
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AcceptTerms(accept = uiState.checkbox) { onAcceptTermChange() }
            ButtonPrimary(
                text = stringResource(id = R.string.exchange_request_button_request),
                loading = uiState.isLoadingRequest,
                enabled = buttonIsEnabled(uiState)
            ) { onButtonClick() }
        }
    }
}

fun buttonIsEnabled(uiState: ExchangeState): Boolean {
    if (
        !uiState.isLoadingBookDetails &&
        uiState.bookSelected != null &&
        uiState.checkbox &&
        !uiState.isLoadingRequest
    ) { return true }

    return false
}
