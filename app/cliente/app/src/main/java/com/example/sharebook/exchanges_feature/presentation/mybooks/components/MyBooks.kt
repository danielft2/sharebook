package com.example.sharebook.exchanges_feature.presentation.mybooks.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.components.loading.LoadingWithBackground
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.exchanges_feature.presentation.mybooks.MyBooksViewModel
import com.example.sharebook.exchanges_feature.presentation.mybooks.channel.DeleteMyBookChannel

@Composable
fun MyBooks(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    myBooksViewModel: MyBooksViewModel = hiltViewModel(),
    onNavigate: (route: String) -> Unit,
) {
    val context = LocalContext.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                myBooksViewModel.getListMyBooks()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(context) {
        myBooksViewModel.deleteBookRequestState.collect {
            when (it) {
                is DeleteMyBookChannel.Success -> {
                    Toast.makeText(
                        context,
                        UiText.StringResource(it.message).asString(context),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is DeleteMyBookChannel.Error -> {
                    Toast.makeText(
                        context,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        FloatingButtonNewBook { onNavigate(PrivateRoutes.AddBookScreen.route) }

        Column(modifier = Modifier
            .background(background)
            .fillMaxSize()) {
            StateWraper(
                onClickTryAgain = { myBooksViewModel.getListMyBooks() },
                isLoading = myBooksViewModel.uiState.isLoading,
                isError = !myBooksViewModel.uiState.isError.isNullOrEmpty()
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(background)) {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 100.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(myBooksViewModel.uiState.listMyBooks) {
                            BookItem(
                                book = it,
                                onNavigate = { onNavigate(PrivateRoutes.UserBook.withArgs(it.id)) },
                                onDelete = { myBooksViewModel.deleteMyBook(it.id) }
                            )
                        }
                    }
                }
            }
        }

        if (myBooksViewModel.uiState.isLoadingDeleteBook) {
            LoadingWithBackground(modifier = Modifier.background(background))
        }
    }
}