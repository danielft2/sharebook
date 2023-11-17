package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green700
import com.example.sharebook.home_feature.domain.model.BookModel

@Composable
fun BookPreview(book: BookModel, onClick: () -> Unit) {
    Column(modifier = Modifier
        .widthIn(max = 110.dp)
        .fillMaxWidth()
        .clickable { onClick() }
    ) {
        ImageCustom(book.coverUrl, modifier = Modifier
            .height(150.dp)
            .width(110.dp)
            .clip(shape = Shapes.small)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.name,
            color = green700,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Lato
        )
        Text(
            text = book.author,
            color = gray500,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 11.sp
        )
    }
}