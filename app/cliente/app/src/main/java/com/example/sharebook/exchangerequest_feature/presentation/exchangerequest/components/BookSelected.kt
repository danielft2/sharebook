package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.book.BookSummary
import com.example.sharebook.core.presentation.ui.theme.red400
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel
import com.example.sharebook.exchanges_feature.domain.model.toBookSummaryModel

@Composable
fun BookSelected(
    book: MyBookModel,
    userLoggedName: String,
    userLoggedPhoto: String,
    onClick: () -> Unit
) {
    Box {
        Box(modifier = Modifier
            .offset(y = 56.dp, x = 36.dp)
            .zIndex(4f)
            .clip(RoundedCornerShape(100))
            .background(red400)
            .size(36.dp)
            .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_reload),
                contentDescription = null
            )
        }
        BookSummary(book = book.toBookSummaryModel(userLoggedName , userLoggedPhoto))
    }
}