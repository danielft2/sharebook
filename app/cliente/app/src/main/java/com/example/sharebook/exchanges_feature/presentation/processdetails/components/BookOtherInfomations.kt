package com.example.sharebook.exchanges_feature.presentation.processdetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.book_feature.presentation.external_book.components.BoolGaleryImage
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.TextButtonMore
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.exchanges_feature.data.local.BookProcessMock

@Composable
fun BookOtherInformations(book: BookProcessMock) {
    Column() {
        Text(
            text = UiText.StringResource(R.string.book_datails_descreption_session).asString(),
            fontFamily = FontFamily(Font(R.font.lato_bold)),
            fontSize = 15.sp,
            color = green900,
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextButtonMore(text = book.description)
    }

    DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)
    BoolGaleryImage()
}