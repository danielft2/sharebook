package com.example.sharebook.book_management_feature.presentation.add_book.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.book_management_feature.presentation.add_book.AddBookViewModel
import com.example.sharebook.core.presentation.components.TextFieldCustom
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Typography
import com.example.sharebook.core.presentation.ui.theme.green800
import com.example.sharebook.core.utils.UiText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddBook(
    viewModel: AddBookViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFF0F8F7), Color(0xFFF8F8F8)),
                        startY = 0f,
                        endY = 500f
                    )
                )
                .padding(0.dp),
        ) {
            AddBookHeader{ navController.popBackStack() }

            Spacer(modifier = Modifier.height(24.dp))

            /*TextFieldCustom(
                label = UiText.StringResource(R.string.field_author).asString(),
                value = null,
                onChange = null
            )*/

        }
    }
}