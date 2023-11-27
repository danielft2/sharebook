package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.book_feature.domain.model.toBookBookSummaryModel
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.components.HeaderWithBackground
import com.example.sharebook.core.presentation.components.IconButtonAction
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.components.book.BookSummarySkeleton
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.components.ChooseBookSheet
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.ExchangeRequestViewModel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.channel.SendRequestChannel
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.event.ExchangeEvent
import com.example.sharebook.exchanges_feature.domain.model.toBookSummaryModel

@Composable
fun ExchangeRequest(
    navController: NavHostController,
    exchangeRequestViewModel: ExchangeRequestViewModel = hiltViewModel()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
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
                        if (exchangeRequestViewModel.uiState.bookSelected != null) {
                            Box {
                                Box(modifier = Modifier
                                    .offset(y = 56.dp, x = 36.dp)
                                    .zIndex(4f)
                                    .clip(RoundedCornerShape(100))
                                    .background(red400)
                                    .size(36.dp)
                                    .clickable { showBottomSheet = true },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_arrow_reload),
                                        contentDescription = null
                                    )
                                }
                                BookSummary(
                                    book = exchangeRequestViewModel.uiState.bookSelected!!
                                        .toBookSummaryModel(
                                            exchangeRequestViewModel.uiState.userLogged?.name ?: "",
                                            exchangeRequestViewModel.uiState.userLogged?.photoUrl ?: ""
                                        ),
                                )
                            }

                        } else { ChooseBook { showBottomSheet = true } }

                        DividerDecorator()

                        if (exchangeRequestViewModel.uiState.isLoadingBookDetails) {
                            BookSummarySkeleton()
                        } else if (exchangeRequestViewModel.uiState.bookRequestDetails != null) {
                            BookSummary(
                                book = exchangeRequestViewModel.uiState.bookRequestDetails!!
                                    .toBookBookSummaryModel()
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier
                    .background(white)
                    .fillMaxWidth()
                    .weight(2f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Checkbox(
                                modifier = Modifier
                                    .background(gray200)
                                    .size(20.dp)
                                    .graphicsLayer(),
                                checked = exchangeRequestViewModel.uiState.checkbox,
                                onCheckedChange = { exchangeRequestViewModel.event(ExchangeEvent.ChangeCheckboxValue) },
                                colors = CheckboxDefaults.colors(
                                    uncheckedColor = gray200,
                                    checkedColor = green600
                                )
                            )
                            Text(
                                text = stringResource(id = R.string.exchange_request_confirm_checkbox),
                                fontFamily = Inter,
                                fontSize = 13.sp,
                                style = TextStyle(lineHeight = (13 * 1.5).sp),
                                color = green900
                            )
                        }

                        ButtonPrimary(
                            text = stringResource(id = R.string.exchange_request_button_request),
                            loading = exchangeRequestViewModel.uiState.isLoadingRequest,
                            enabled = !exchangeRequestViewModel.uiState.isLoadingBookDetails
                        ) { exchangeRequestViewModel.event(ExchangeEvent.SendRequest) }
                    }
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
