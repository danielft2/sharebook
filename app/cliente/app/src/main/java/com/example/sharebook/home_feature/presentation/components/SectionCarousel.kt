package com.example.sharebook.home_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.*


@Composable
fun SectionCarousel(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = green900,
                fontSize = 17.sp,
                fontFamily = Lato,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(Color.Transparent)
                    .padding(0.dp)
                    .size(28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_chevron_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 1.dp, bottom = 1.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        content()
    }
}