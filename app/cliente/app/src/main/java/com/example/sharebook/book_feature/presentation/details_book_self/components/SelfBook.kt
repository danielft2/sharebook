package com.example.sharebook.book_feature.presentation.details_book_self.components

import androidx.compose.runtime.Composable
import com.example.sharebook.book_feature.presentation.details_book_self.SelfBookViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.book_feature.domain.model.toBookBookYourSummaryModel
import com.example.sharebook.book_feature.presentation.details_book_external.components.BookGaleryImage
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.components.divider.DividerCustom
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.utils.UiText

@Composable
fun SelfBook(
    selfViewModel: SelfBookViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState = selfViewModel.uiState

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column (modifier = Modifier
            .background(white)
            .fillMaxSize()
        ) {
            HeaderWithBackground {
                IconButtonAction(
                    resource = R.drawable.icon_arrow_back,
                    sizeValue = 28,
                    modifier = Modifier.background(white)
                ) { navController.popBackStack() }

                Text(
                    text = UiText.StringResource(R.string.book_details_header).asString(),
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )

                IconButtonAction(
                    resource = R.drawable.icon_edit,
                    sizeValue = 24,
                    modifier = Modifier
                ) {  }
            }

            StateWraper(
                onClickTryAgain = {  },
                isLoading = uiState.isLoadingDetails,
                isError = !uiState.isErrorDetails.isNullOrEmpty()
            ) {
                Column(modifier = Modifier
                    .padding(top = 0.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier
                        .weight(1f, fill = false)
                        .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        BookSummary(book = uiState.bookDetails!!.toBookBookYourSummaryModel())

                        DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)

                        Column {
                            Text(
                                text = UiText.StringResource(R.string.book_datails_descreption_session).asString(),
                                fontFamily = FontFamily(Font(R.font.lato_bold)),
                                fontSize = 15.sp,
                                color = green900,
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            TextButtonMore(text = uiState.bookDetails.synopsis)
                        }

                        DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)
                        BookGaleryImage(images = uiState.bookDetails.images)

                        DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)
                    }
                }
            }
        }

    }
}