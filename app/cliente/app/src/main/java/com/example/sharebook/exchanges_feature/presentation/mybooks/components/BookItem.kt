package com.example.sharebook.exchanges_feature.presentation.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.book.BookTag
import com.example.sharebook.core.presentation.components.divider.DividerCustom
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.components.book.BookCoverSkeleton
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

@Composable
fun BookItem(
    book: MyBookModel,
    onNavigate: () -> Unit,
    onDelete: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(white)
    ) {
        Row {
            ImageCustom(
                url = book.cover,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(115.dp)
                    .clickable { onNavigate() }
            ) {
                BookCoverSkeleton(Modifier
                .fillMaxHeight()
                .width(115.dp))
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Row {
                    Column (
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "${book.genders} - ${book.edition}° Edição",
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
                            text = book.authors,
                            color = gray500,
                            fontSize = 14.sp,
                            fontFamily = Lato,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp).weight(1f).clickable { onDelete() },
                        tint = Color.Red
                    )
                }


                DividerCustom(spaceTop = 16.dp, spaceBottom = 16.dp)

                BookTag(
                    text = book.bookState,
                    background = blue100,
                    colorText = blue500
                )
                Spacer(modifier = Modifier.height(8.dp))
                BookTag(text = book.preference.tag, background = green100, colorText = green600)
            }
        }
    }
}