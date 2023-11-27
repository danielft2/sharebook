package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.domain.model.BookSummaryModel
import com.example.sharebook.core.presentation.components.BookTag
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun BookSummary(book: BookSummaryModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageCustom(url = book.coverUrl, modifier = Modifier
                .width(108.dp)
                .height(160.dp)
                .clip(Shapes.medium)
            ) {
                BookCoverSkeleton(modifier = Modifier.width(108.dp).height(160.dp))
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column {
                    Text(
                        text = "${book.genders} - ${book.edition} Edição",
                        color = green500,
                        fontFamily = FontFamily(Font(R.font.inter_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = book.name,
                        color = green900,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = book.authors,
                        color = gray500,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BookTag(text = book.bookState, background = blue100, colorText = blue500)
                        BookTag(text = book.preference.tag, background = green100, colorText = green600)
                    }
                }

                BookOwnerInformations(
                    name = book.userName,
                    secondaryText = book.secondaryText,
                    profileUrl = book.userProfilePhoto ?: ""
                )
            }
        }
    }
}