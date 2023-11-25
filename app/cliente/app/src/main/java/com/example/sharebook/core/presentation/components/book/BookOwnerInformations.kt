package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun BookOwnerInformations(name: String, secondaryText: String) {
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
                text = name,
                fontFamily = Inter,
                fontSize = 12.sp,
                color = green900,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = secondaryText,
                fontFamily = Inter,
                fontSize = 12.sp,
                color = gray500,
                fontWeight = FontWeight.Medium
            )
        }
    }
}