package com.example.sharebook.book_feature.presentation.details_book_external.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.components.book.BookCoverSkeleton
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun BookGaleryImage(images: List<String>) {
    Column {
        Text(
            text = stringResource(id = R.string.book_datails_gallery_session),
            fontFamily = FontFamily(Font(R.font.lato_bold)),
            fontSize = 15.sp,
            color = green900
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (images.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(images) {
                    ImageCustom(
                        url = it,
                        modifier = Modifier
                            .width(100.dp)
                            .height(146.dp)
                            .clip(RoundedCornerShape(6.dp))
                    ) {
                        BookCoverSkeleton(
                            modifier = Modifier
                                .width(100.dp)
                                .height(146.dp)
                        )
                    }
                }
            }
        } else {
            Text(
                text = "O livro não possui nenhuma imagem extra.",
                fontSize = 14.sp,
                fontFamily = Inter,
                color = gray500
            )
        }
    }
}