package com.example.sharebook.exchanges_feature.presentation.enteredprocess.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.BookTag
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchanges_feature.domain.model.EnteredProcessBookModel

@Composable
fun BookItem(book: EnteredProcessBookModel, onClick: () -> Unit) {
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
                        text = "${book.gender} - ${book.edtion}",
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
                    text = book.state.title,
                    background = blue100,
                    colorText = blue500
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(22.dp))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = book.userName,
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = green900,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = book.userLocation,
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = gray500,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}