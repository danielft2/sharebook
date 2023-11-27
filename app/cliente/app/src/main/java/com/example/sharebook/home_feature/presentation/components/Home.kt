package com.example.sharebook.home_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.FloatingButtonNewBook
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.home_feature.presentation.HomeViewModel

@Composable
fun Home(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        FloatingButtonNewBook {}

        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)) {
            Header(homeViewModel, navController)

            StateWraper(
                onClickTryAgain = { homeViewModel.listBooks() },
                isLoading = homeViewModel.listBooksRequestState.isLoading,
                isError = !homeViewModel.listBooksRequestState.error.isNullOrEmpty()
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Column (
                    modifier = Modifier
                        .padding(16.dp, 8.dp, end = 4.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    SectionCarousel(
                        title = stringResource(R.string.home_carousel_available),
                        listBook = homeViewModel.uiState.availableBooks,
                        onClickBook = { bookId ->
                            navController.navigate(PrivateRoutes.ExternalBook.withArgs(bookId))
                        }
                    )

                    SectionCarousel(
                        title = stringResource(R.string.home_carousel_nearToYou),
                        listBook = homeViewModel.uiState.nextToYou,
                        onClickBook = { bookId ->
                            navController.navigate(PrivateRoutes.ExternalBook.withArgs(bookId))
                        }
                    )

                    Spacer(modifier = Modifier.height(132.dp))
                }
            }
        }
    }
}