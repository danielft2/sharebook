package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.HeaderWithBackground
import com.example.sharebook.core.presentation.components.button.IconButtonAction
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.components.ChooseBookSheet
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.ExchangeRequestViewModel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.channel.SendRequestChannel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event.ExchangeEvent

@Composable
fun ExchangeRequest(
    navController: NavHostController,
    exchangeRequestViewModel: ExchangeRequestViewModel = hiltViewModel()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val uiState = exchangeRequestViewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(context) {
        exchangeRequestViewModel.requestChannelState.collect {
            when (it) {
                is SendRequestChannel.SuccessRequest -> {
                    Toast.makeText(context, "Solicitação enviada com sucesso", Toast.LENGTH_LONG).show()
                }
                is SendRequestChannel.ErrorRequest -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Column(modifier = Modifier
                .background(background)
                .fillMaxSize()
            ) {
                HeaderWithBackground {
                    IconButtonAction(
                        resource = R.drawable.icon_arrow_back,
                        sizeValue = 28,
                        modifier = Modifier.background(white)
                    ) { navController.popBackStack() }

                    Text(
                        text = stringResource(R.string.exchange_request_header_title),
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(2f)
                            .padding(end = 44.dp),
                    )
                }

                Column(modifier = Modifier.background(white)) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
                        BookUserLoggedConditional(uiState) { showBottomSheet = true }
                        DividerDecorator()
                        BookSummaryConditional(uiState)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier
                    .background(white)
                    .fillMaxWidth()
                    .weight(2f),
                    horizontalAlignment = Alignment.End
                ) {
                    FooterConditional(
                        uiState = uiState,
                        onButtonClick = {
                            exchangeRequestViewModel.event(ExchangeEvent.SendRequest)
                        },
                        onAcceptTermChange = {
                            exchangeRequestViewModel.event(ExchangeEvent.ChangeCheckboxValue)
                        }
                    )
                }
            }

            ChooseBookSheet(
                onDismiss = { showBottomSheet = false },
                onSelected = { exchangeRequestViewModel.event(ExchangeEvent.SelectedBook(it)) },
                maxHeight = maxHeight,
                showBottomSheet = showBottomSheet
            )
        }
    }
}

