package com.example.sharebook.exchanges_feature.presentation.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.domain.model.BookSummaryModel
import com.example.sharebook.core.domain.model.BookUserLoggedModel
import com.example.sharebook.core.presentation.components.BookTag
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun BookItem(book: BookUserLoggedModel, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(white)
        .clickable { onClick() }
    ) {
        Row {
            ImageCustom(
                url = book.coverUrl,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(115.dp)
            )
            Column(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        text = "${book.gender} - ${book.edition}",
                        color = green600,
                        fontSize = 14.sp,
                        fontFamily = Lato,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = book.name,
                        color = green900,
                        fontSize = 15.sp,
                        fontFamily = Lato,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = book.author,
                        color = gray500,
                        fontSize = 14.sp,
                        fontFamily = Lato,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                DividerCustom(spaceTop = 16.dp, spaceBottom = 16.dp)

                BookTag(
                    text = book.state.tag,
                    background = blue100,
                    colorText = blue500
                )
                Spacer(modifier = Modifier.height(8.dp))
                BookTag(text = book.preference.tag, background = green100, colorText = green600)
            }
        }
    }
}