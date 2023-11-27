package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.components.book.BookCoverSkeleton
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel

@Composable
fun BookItemRow(book: MyBookModel, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCustom(
                url = book.cover,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(shape = Shapes.small)
            ) {
                BookCoverSkeleton(modifier = Modifier.width(48.dp).height(48.dp))
            }

            Column {
                Text(
                    text = book.name,
                    fontSize = 13.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    color = green900
                )
                Text(
                    text = book.authors,
                    fontSize = 13.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    color = green600
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.icon_chevron_right),
            contentDescription = null,
            tint = gray500
        )
    }
}