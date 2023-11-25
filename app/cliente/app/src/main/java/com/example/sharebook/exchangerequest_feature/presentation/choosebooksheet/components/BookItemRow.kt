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
import com.example.sharebook.core.domain.model.BookUserLoggedModel
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun BookItemRow(book: BookUserLoggedModel, onClick: () -> Unit) {
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
                url = book.coverUrl,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(shape = Shapes.small)
            )
            Column {
                Text(
                    text = book.name,
                    fontSize = 13.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    color = green900
                )
                Text(
                    text = book.author,
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