package com.example.sharebook.exchanges_feature.presentation.request_details.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.presentation.components.book.BookTag
import com.example.sharebook.core.presentation.components.divider.DividerCustom
import com.example.sharebook.core.presentation.components.HeaderWithBackground
import com.example.sharebook.core.presentation.components.IconButtonAction
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.components.loading.LoadingWithBackground
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components.DividerDecorator
import com.example.sharebook.exchanges_feature.presentation.request_details.RequestDetailsViewModel
import com.example.sharebook.exchanges_feature.presentation.request_details.channel.UpdateRequestChannel
import com.example.sharebook.exchanges_feature.presentation.request_details.event.RequestDetailsEvent

@Composable
fun RequestDetails(
    navController: NavHostController,
    requestDetailsViewModel: RequestDetailsViewModel = hiltViewModel(),
) {
    val uiState = requestDetailsViewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(LocalContext.current) {
        requestDetailsViewModel.updateRquestChannelState.collect { response ->
            when (response) {
                is UpdateRequestChannel.Success -> {
                    Toast.makeText(
                        context,
                        UiText.StringResource(response.messageReource!!).asString(context),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is UpdateRequestChannel.Error -> {
                    Toast.makeText(
                        context, response.message, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize().background(background)) {
            Column(modifier = Modifier.fillMaxSize()) {
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
                    onClickTryAgain = { requestDetailsViewModel.onEvent(RequestDetailsEvent.ListDetails) },
                    isLoading = uiState.isLoadingDetails,
                    isError = !uiState.isErrorDetails.isNullOrEmpty()
                ) {
                    val statusColor = Functions.getColorsByRequestStatus(status = uiState.requestDetails!!.status)

                    Column(
                        modifier = Modifier
                            .background(white)
                            .verticalScroll(rememberScrollState())
                            .weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            BookSummary(book = uiState.requestDetails.userLoggedBook)
                            DividerDecorator()

                            Text(
                                text = stringResource(id = R.string.request_details_book_offered),
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = green900
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            BookSummary(book = uiState.requestDetails.userExternalBook)

                            DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)

                            BookTag(
                                background = statusColor.background,
                                colorText = statusColor.colorText,
                                text = uiState.requestDetails.status.tag,
                                textSize = 13.sp
                            )

                            if (
                                uiState.requestDetails.status == BookRequestStatus.ACCEPTED ||
                                uiState.requestDetails.status == BookRequestStatus.FINALIZE
                            ) {
                                DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)
                                UserExternalInformations(
                                    phone = uiState.requestDetails.userExternalPhone,
                                    location = uiState.requestDetails.userExternalLocation
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier
                            .background(white)
                            .wrapContentSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            ButtonConditional(
                                bookRequestStatus = uiState.requestDetails.status,
                                isLoading = uiState.isLoadingUpdateRequest,
                                isRequestFromUserLogged = uiState.requestDetails.userRequestId ==
                                        uiState.userLogged!!.id,
                                onChangeStatusRequest = { status ->
                                    requestDetailsViewModel.onEvent(RequestDetailsEvent.UpdateStatusRequest(status))
                                },
                                onNavigateBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }

            if (uiState.isLoadingRefreshDetails) LoadingWithBackground()
        }
    }
}