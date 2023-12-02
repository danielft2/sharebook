package com.example.sharebook.book_feature.presentation.form_book.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.TextFieldCustom

@Composable
fun Form() {
    Column(modifier = Modifier.fillMaxSize()) {
        TextFieldCustom(label = "ISBN", value = "", onChange = {})
        TextFieldCustom(label = "Gênero(s)", value = "", onChange = {})
        TextFieldCustom(label = "Autor(es)", value = "", onChange = {})
        TextFieldCustom(label = "Edição", value = "", onChange = {})
        TextFieldCustom(label = "Idioma", value = "", onChange = {})

        DividerCustom(spaceTop = 16.dp, spaceBottom = 16.dp)
    }
}