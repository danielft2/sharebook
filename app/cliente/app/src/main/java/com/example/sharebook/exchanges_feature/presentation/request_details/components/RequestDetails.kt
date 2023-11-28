package com.example.sharebook.exchanges_feature.presentation.request_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.HeaderWithBackground
import com.example.sharebook.core.presentation.components.IconButtonAction
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components.DividerDecorator
import com.example.sharebook.exchanges_feature.presentation.request_details.RequestDetailsViewModel

@Composable
fun RequestDetails(
    navController: NavHostController,
    requestDetailsViewModel: RequestDetailsViewModel = hiltViewModel()
) {
    val uiState = requestDetailsViewModel.uiState

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)) {
            HeaderWithBackground {
                IconButtonAction(
                    resource = R.drawable.icon_arrow_back,
                    sizeValue = 28,
                    modifier = Modifier.background(white)
                ) { navController.popBackStack() }

                Text(
                    text = stringResource(R.string.request_details_header),
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 44.dp),
                )
            }

            StateWraper(
                onClickTryAgain = { requestDetailsViewModel.getRequestDetails(requestDetailsViewModel.requestId) },
                isLoading = uiState.isLoadingDetails,
                isError = !uiState.isErrorDetails.isNullOrEmpty()
            ) {
                Column(modifier = Modifier.background(white)) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
                        BookSummary(book = uiState.requestDetails!!.userLoggedRequest)
                        DividerDecorator()

                        Text(
                            text = "Livro ofertado na troca",
                            fontFamily = Lato,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = green900
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))

                        BookSummary(book = uiState.requestDetails.extertalUserRequest)
                    }
                }
            }
        }
    }
}