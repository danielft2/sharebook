package com.example.sharebook.book_feature.presentation.form_book.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.book_feature.presentation.form_book.FormBookViewModel
import com.example.sharebook.book_feature.presentation.form_book.channel.FormBookRequestChannel
import com.example.sharebook.book_feature.presentation.form_book.event.FormBookEvent
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.core.utils.UiText

@Composable
fun FormBook(
    formBookViewModel: FormBookViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState = formBookViewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(context) {
        formBookViewModel.formBookRequestState.collect {
            when (it) {
                is FormBookRequestChannel.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }

                is FormBookRequestChannel.Success -> {
                    Toast.makeText(
                        context,
                        UiText.StringResource(it.message).asString(context),
                        Toast.LENGTH_LONG
                    ).show()

                    navController.popBackStack()
                }
            }
        }
    }

     Surface(modifier = Modifier.fillMaxSize()) {
         Column(modifier = Modifier
             .fillMaxSize()
             .background(white)
             .padding(16.dp),
             verticalArrangement = Arrangement.spacedBy(12.dp)
         ) {
             HeaderForm { navController.popBackStack() }
             StateWraper(
                 onClickTryAgain = { },
                 isLoading = uiState.isLoadingStateRequest,
                 isError = !uiState.isErrorStateRequest.isNullOrEmpty()
             ) {
                 Column(modifier = Modifier.weight(1f)) {
                     Form(uiState) { formBookViewModel.onEvent(it) }
                 }
                 Column(modifier = Modifier.padding(top = 32.dp)) {
                     ButtonPrimary(text = "Confirmar", loading = uiState.isLoadingFormRequest) {
                         formBookViewModel.onEvent(FormBookEvent.Submit)
                     }
                 }
             }
         }
     }
}