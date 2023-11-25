package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun ChooseBook(onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .width(110.dp)
            .clip(shape = Shapes.small)
            .background(gray200),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(100))
                .background(green600),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_file_add),
                    contentDescription = null,
                    Modifier.size(24.dp)
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.exchange_request_choose_book_title),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lato,
                    color = green900
                )

                Text(
                    text = stringResource(R.string.exchange_request_choose_book_description),
                    fontFamily = Inter,
                    fontSize = 13.sp,
                    color = gray500
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(gray200),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "DA",
                        fontFamily = Lato,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = gray500
                    )
                }

                Column {
                    Text(
                        text = stringResource(id = R.string.exchange_request_user_logged),
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = green900,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = stringResource(id = R.string.exchange_request_owner_book),
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