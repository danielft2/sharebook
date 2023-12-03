package com.example.sharebook.book_feature.presentation.form_book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.green400
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun HeaderForm(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(2.dp)
                .background(Color.Transparent)
                .border(width = 1.6.dp, green400, RoundedCornerShape(100))
                .width(40.dp)
                .height(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_back),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                    .padding(end = 2.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.form_book_title),
            fontFamily = Lato,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = green900,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f).padding(end = 32.dp)
        )
    }
}
