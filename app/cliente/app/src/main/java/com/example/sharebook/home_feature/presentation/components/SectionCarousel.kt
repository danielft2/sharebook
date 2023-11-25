package com.example.sharebook.home_feature.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.book.BookPreview
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.home_feature.domain.model.BookPreviewModel


@Composable
fun SectionCarousel(
    modifier: Modifier = Modifier,
    title: String,
    listBook: List<BookPreviewModel>,
    onClickBook: (id: String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = green900,
            fontSize = 17.sp,
            fontFamily = Lato,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(listBook) {
                BookPreview(
                    book = it,
                    onClick = { onClickBook(it.id) }
                )
            }
        }
    }
}